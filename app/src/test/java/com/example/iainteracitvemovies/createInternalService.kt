package com.example.iainteracitvemovies

import com.example.iainteracitvemovies.data.network.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
fun createInternalService(baseUrl: String): APIService {
    val okHttpClient = OkHttpClient
        .Builder()
        .build()
    return Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build().create(APIService::class.java)
}