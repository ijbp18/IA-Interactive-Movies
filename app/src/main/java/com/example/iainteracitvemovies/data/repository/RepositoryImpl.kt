package com.example.iainteracitvemovies.data.repository

import com.example.iainteracitvemovies.data.network.APIService
import com.example.iainteracitvemovies.data.network.mappers.mapFromUserEntity
import com.example.iainteracitvemovies.data.repository.utils.BaseRepository
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.utils.DispatcherProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
class RepositoryImpl @Inject constructor(
    private val myServiceAPI: APIService,
    private val dispatchers: DispatcherProvider
) : Repository, BaseRepository() {

    override fun validateUserInfo(objectBody : String): Flow<Result<UserUI>> {
        return flow<Result<UserUI>> {

            val responseLogin = myServiceAPI.validateUserLogin(objectBody)

            val authorization = "${responseLogin.token_type} ${responseLogin.access_token}"

            val responseUserInfo = myServiceAPI.getUserInfo(authorization)
            emit(Result.Success(data = mapFromUserEntity(responseUserInfo)))

        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(handlerErrorException(throwable = it))
        }.flowOn(dispatchers.io())
    }

}