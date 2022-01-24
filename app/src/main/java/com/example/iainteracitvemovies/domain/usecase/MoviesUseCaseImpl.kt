package com.example.iainteracitvemovies.domain.usecase

import com.example.iainteracitvemovies.data.repository.Repository
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieInfoUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
class MoviesUseCaseImpl @Inject constructor(private val remoteRepository: Repository) :
    MoviesUseCase {
    override suspend fun invoke(): Flow<Result<MovieInfoUI>> {
        return remoteRepository.getMovies()
    }
}