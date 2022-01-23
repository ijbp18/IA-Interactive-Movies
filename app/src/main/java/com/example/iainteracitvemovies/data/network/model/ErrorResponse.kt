package com.example.iainteracitvemovies.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
data class ErrorResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("error_description")
    val error_description: String
)