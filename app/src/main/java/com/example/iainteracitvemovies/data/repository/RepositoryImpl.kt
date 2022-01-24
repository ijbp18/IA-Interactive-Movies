package com.example.iainteracitvemovies.data.repository

import com.example.iainteracitvemovies.data.network.APIService
import com.example.iainteracitvemovies.data.network.APIServiceAWS
import com.example.iainteracitvemovies.data.network.Constants.CINEMA_VALUE
import com.example.iainteracitvemovies.data.network.Constants.COUNTRY_CODE
import com.example.iainteracitvemovies.data.network.mappers.toMovieUIList
import com.example.iainteracitvemovies.data.network.mappers.toUserUnfoUI
import com.example.iainteracitvemovies.data.repository.utils.BaseRepository
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.MovieInfoUI
import com.example.iainteracitvemovies.utils.DispatcherProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
class RepositoryImpl @Inject constructor(
    private val myServiceAPI: APIService,
    private val myServiceAWS: APIServiceAWS,
    private val dispatchers: DispatcherProvider
) : Repository, BaseRepository() {

    override fun validateUserInfo(objectBody: String): Flow<Result<UserUI>> {
        return flow<Result<UserUI>> {

            val responseLogin = myServiceAPI.validateUserLogin(objectBody)

            val authorization = "${responseLogin.token_type} ${responseLogin.access_token}"
            val responseUserInfo = myServiceAPI.getUserInfo(authorization, COUNTRY_CODE)
            emit(Result.Success(data = responseUserInfo.toUserUnfoUI()))

        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(handlerErrorException(throwable = it))
        }.flowOn(dispatchers.io())
    }

    override fun getMovies(): Flow<Result<MovieInfoUI>> {
        return flow<Result<MovieInfoUI>> {

            val response = myServiceAWS.getMovies(COUNTRY_CODE, CINEMA_VALUE)
            emit(Result.Success(data = response.toMovieUIList()))

        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(handlerErrorException(throwable = it))
        }.flowOn(dispatchers.io())
    }

}