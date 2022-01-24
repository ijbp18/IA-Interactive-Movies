package com.example.iainteracitvemovies.data.network

import com.example.iainteracitvemovies.data.network.Constants.CINEMA_KEY
import com.example.iainteracitvemovies.data.network.Constants.COUNTRY_CODE_KEY
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
interface APIServiceAWS {
    @GET(GET_MOVIES)
    suspend fun getMovies(@Query(COUNTRY_CODE_KEY) country_code: String, @Query(CINEMA_KEY) cinema_code: String): MoviesResponse
}