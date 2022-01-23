package com.example.iainteracitvemovies.data.network.mappers

import com.example.iainteracitvemovies.data.network.model.UserResponse
import com.example.iainteracitvemovies.domain.entities.UserUI

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

fun mapFromUserEntity(response: UserResponse): UserUI {
    return UserUI(
        response.email,
        response.first_name,
        response.last_name,
        response.phone_number,
        response.profile_picture,
        response.card_number
    )
}