package com.example.iainteracitvemovies.data.repository

import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieUI
import kotlinx.coroutines.flow.Flow

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
interface Repository {
    fun validateUserInfo(objectBody: String): Flow<Result<UserUI>>
    fun getMovies(): Flow<Result<MovieUI>>
}