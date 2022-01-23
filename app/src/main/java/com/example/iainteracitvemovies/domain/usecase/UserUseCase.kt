package com.example.iainteracitvemovies.domain.usecase

import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.common.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
interface UserUseCase {
    suspend fun invoke(objectBody: String): Flow<Result<UserUI>>
}