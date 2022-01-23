package com.example.iainteracitvemovies.domain.usecase

import com.example.iainteracitvemovies.data.repository.Repository
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.common.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
class UserUseCaseImpl @Inject constructor(private val remoteRepository: Repository) :
    UserUseCase {
    override suspend fun invoke(objectBody: String): Flow<Result<UserUI>> {
        return remoteRepository.validateUserInfo(objectBody)
    }
}