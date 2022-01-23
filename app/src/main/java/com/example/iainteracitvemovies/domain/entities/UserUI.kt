package com.example.iainteracitvemovies.domain.entities
import java.io.Serializable
/**
 * Created by Joao Betancourth on 22,enero,2022
 */
data class UserUI(
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val profile_picture: String,
    val card_number: String
):Serializable
