package com.example.iainteracitvemovies.data.network.mappers

import com.example.iainteracitvemovies.data.network.model.ErrorResponse
import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

fun ErrorResponse.toErrorUI() = ErrorResponseUI(
    error = error,
    error_description = error_description
)