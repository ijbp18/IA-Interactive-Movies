package com.example.iainteracitvemovies.domain.common

import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI

sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    object Loading : Result<Nothing>()
    data class Failure<T>(val error: ErrorResponseUI?, val httpCode: Int) : Result<T>()
}