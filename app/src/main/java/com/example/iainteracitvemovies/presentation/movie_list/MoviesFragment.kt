package com.example.iainteracitvemovies.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.iainteracitvemovies.common.BaseFragment
import com.example.iainteracitvemovies.databinding.FragmentMoviesBinding
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieInfoUI
import com.example.iainteracitvemovies.domain.entities.MovieUI
import com.example.iainteracitvemovies.presentation.utils.Communicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment(), MoviesAdapter.ItemSelectedListener {

    private lateinit var binding: FragmentMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val moviesAdapter: MoviesAdapter = MoviesAdapter(this@MoviesFragment)
    private var imageRouteUrlBase = ""
    private var videoRouteUrlBase = ""
    private lateinit var communicator: Communicator

    override fun onMovieSelected(movie: MovieUI) {
        communicator.passMovieData(movie, videoRouteUrlBase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        communicator = activity as Communicator
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
        getMovieList()
    }

    private fun initUI() {
        binding.recyclerMovies.adapter = moviesAdapter
    }

    private fun initObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success<MovieInfoUI> -> {
                    binding.moviesLoader.visibility = View.GONE
                    showMovieList(it.data)
                }
                is Result.Loading -> {
                    binding.moviesLoader.visibility = View.VISIBLE
                }
                is Result.Failure -> {
                    binding.moviesLoader.visibility = View.GONE
                    showMessageFromBackend(it.error, it.httpCode) {
                    }
                }
            }
        })
    }

    private fun showMovieList(movies: MovieInfoUI?) {

        movies?.routes?.let { routeList ->
            routeList.forEach { item ->
                when (item.code) {
                    IMAGE_ROUTE_KEY -> {
                        imageRouteUrlBase = item.sizes.medium
                    }
                    VIDEO_ROUTE_KEY -> {
                        videoRouteUrlBase = item.sizes.medium
                    }
                    else -> {
                        //nothing to do
                    }
                }
            }
        }
        movies?.let {
            moviesAdapter.setData(it.movies, imageRouteUrlBase)
        }
    }

    private fun getMovieList() {
        moviesViewModel.retrieveMoviesDefault()
    }

    companion object {
        const val IMAGE_ROUTE_KEY = "poster"
        const val VIDEO_ROUTE_KEY = "trailer_mp4"
    }

}