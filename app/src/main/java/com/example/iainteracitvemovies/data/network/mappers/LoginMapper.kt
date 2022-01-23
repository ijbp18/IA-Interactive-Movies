package com.example.iainteracitvemovies.data.network.mappers

import com.example.iainteracitvemovies.data.network.model.UserResponse
import com.example.iainteracitvemovies.domain.entities.UserUI

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

fun UserResponse.toUserUnfoUI(): UserUI {
    return UserUI(
        this.email,
        this.first_name,
        this.last_name,
        this.phone_number,
        this.profile_picture,
        this.card_number
    )
}