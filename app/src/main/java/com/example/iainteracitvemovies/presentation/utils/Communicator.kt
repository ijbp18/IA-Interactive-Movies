package com.example.iainteracitvemovies.presentation.utils

import com.example.iainteracitvemovies.domain.entities.MovieUI

/**
 * Created by Joao Betancourth on 24,enero,2022
 */
interface Communicator {
    fun passMovieData(movieSelected: MovieUI)
}