package com.example.iainteracitvemovies.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.iainteracitvemovies.common.BaseFragment
import com.example.iainteracitvemovies.databinding.FragmentMoviesBinding
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val moviesViewModel : MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        getMovieList()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success<MovieUI> -> {
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

    private fun showMovieList(data: MovieUI?) {
        data?.let {
            Toast.makeText(activity, "*** Test -> ${it.movies[0].rating}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getMovieList() {
        moviesViewModel.retrieveMoviesDefault()
    }

}