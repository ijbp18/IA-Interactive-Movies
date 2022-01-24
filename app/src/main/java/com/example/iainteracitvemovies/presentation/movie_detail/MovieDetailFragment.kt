package com.example.iainteracitvemovies.presentation.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.databinding.FragmentMovieDetailBinding
import com.example.iainteracitvemovies.databinding.FragmentMoviesBinding
import com.example.iainteracitvemovies.domain.entities.MovieUI
import com.example.iainteracitvemovies.presentation.user_info.HomeActivity.Companion.SELECTED_MOVIE_KEY
import com.example.iainteracitvemovies.presentation.utils.Communicator

class MovieDetailFragment : Fragment() {

    private lateinit var movieSelected : MovieUI
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        getBundleExtra()
        return binding.root
    }

    private fun getBundleExtra() {
        movieSelected = arguments?.getSerializable(SELECTED_MOVIE_KEY) as MovieUI
        movieSelected.let {
            Toast.makeText(activity, "Detail name -> ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }
}