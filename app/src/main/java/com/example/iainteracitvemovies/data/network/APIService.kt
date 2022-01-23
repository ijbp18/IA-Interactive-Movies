package com.example.iainteracitvemovies.data.network

import com.example.iainteracitvemovies.data.network.Endpoints.Companion.GET_MOVIES
import com.example.iainteracitvemovies.data.network.Endpoints.Companion.GET_USER
import com.example.iainteracitvemovies.data.network.Endpoints.Companion.VALIDATE_USER
import com.example.iainteracitvemovies.data.network.model.LoginResponse
import com.example.iainteracitvemovies.data.network.model.MoviesResponse
import com.example.iainteracitvemovies.data.network.model.UserResponse
import retrofit2.http.*

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
interface APIService {
    @POST(VALIDATE_USER)
    suspend fun validateUserLogin(@Body body: String): LoginResponse

    @GET(GET_USER)
    suspend fun getUserInfo(@Header("Authorization") authorization: String): UserResponse

    @GET(GET_MOVIES)
    suspend fun getMovies(): MoviesResponse
}