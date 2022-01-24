package com.example.iainteracitvemovies.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieInfoUI
import com.example.iainteracitvemovies.domain.entities.MovieUI
import com.example.iainteracitvemovies.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<Result<MovieInfoUI>>()
    val movies: LiveData<Result<MovieInfoUI>> get() = _movies

    fun retrieveMoviesDefault() {
        viewModelScope.launch {
            moviesUseCase.invoke()
                .collect {
                    _movies.value = it
                }
        }
    }

}