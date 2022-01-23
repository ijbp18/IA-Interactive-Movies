package com.example.iainteracitvemovies.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
data class UserResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("profile_picture")
    val profile_picture: String,
    @SerializedName("card_number")
    val card_number: String,
)
