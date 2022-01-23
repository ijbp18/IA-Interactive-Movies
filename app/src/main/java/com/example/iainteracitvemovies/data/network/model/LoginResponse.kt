package com.example.iainteracitvemovies.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
data class LoginResponse(
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("token_type")
    val token_type: String
)
