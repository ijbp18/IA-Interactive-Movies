package com.example.iainteracitvemovies.presentation.movie_detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.iainteracitvemovies.databinding.FragmentMovieDetailBinding
import com.example.iainteracitvemovies.domain.entities.MediaUI
import com.example.iainteracitvemovies.domain.entities.MovieUI
import com.example.iainteracitvemovies.presentation.movie_list.MoviesFragment
import com.example.iainteracitvemovies.presentation.user_info.HomeActivity.Companion.SELECTED_MOVIE_KEY
import com.example.iainteracitvemovies.presentation.user_info.HomeActivity.Companion.SELECTED_URL_MOVIE_KEY
import java.lang.StringBuilder

class MovieDetailFragment : Fragment() {

    private lateinit var movieSelected : MovieUI
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var videoController: MediaController
    private lateinit var videoRouteUrlBase : String
    private lateinit var videoRouteUrlEndpoint : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        getBundleExtra()
        setInfoMovie()
        configVideoPlayer()
        return binding.root
    }

    private fun setInfoMovie() {

        binding.apply {
            txtName.text = movieSelected.name
            txtSynopsis.text = movieSelected.synopsis
            txtGenre.text = movieSelected.genre
            txtRating.text = movieSelected.rating
            txtDuration.text = movieSelected.length
        }
    }


    private fun getBundleExtra() {
        movieSelected = arguments?.getSerializable(SELECTED_MOVIE_KEY) as MovieUI
        videoRouteUrlBase = arguments?.getString(SELECTED_URL_MOVIE_KEY) as String
        getVideoEndpoint(movieSelected.media)

    }

    private fun configVideoPlayer() {
        videoController = MediaController(activity)
        videoController.setAnchorView(binding.viewMovie)

        binding.apply {
            viewMovie.setMediaController(videoController)
            viewMovie.setVideoURI(Uri.parse(StringBuilder().append(videoRouteUrlBase).append(videoRouteUrlEndpoint).toString()))
            viewMovie.start()
        }

    }

    private fun getVideoEndpoint(media: ArrayList<MediaUI>) {
        media.let { mediaList ->
            mediaList.forEach { item ->
                if (item.code == MoviesFragment.VIDEO_ROUTE_KEY) {
                    videoRouteUrlEndpoint = item.resource
                }
            }
        }
    }
}