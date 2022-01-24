package com.example.iainteracitvemovies.domain.usecase

import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieInfoUI
import kotlinx.coroutines.flow.Flow

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
interface MoviesUseCase {
    suspend fun invoke(): Flow<Result<MovieInfoUI>>
}