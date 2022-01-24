package com.example.iainteracitvemovies.data.repository.utils

import com.example.iainteracitvemovies.data.network.mappers.toErrorUI
import com.example.iainteracitvemovies.data.network.model.ErrorResponse
import retrofit2.HttpException
import java.io.IOException
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI
import com.google.gson.GsonBuilder

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
open class BaseRepository {

    private fun parseError(
        httpException: HttpException
    ): ErrorResponseUI? {
        return try {
            val gson = GsonBuilder().create()
            val messageError = httpException.response()?.errorBody()?.string()
            val errorResponse = gson.fromJson(messageError, ErrorResponse::class.java)
            errorResponse.toErrorUI()
        } catch (e: Exception) {
            null
        }
    }

    fun <T> handlerErrorException(
        throwable: Throwable
    ): Result<T> {
        return when (throwable) {
            is HttpException -> {
                val error = parseError(
                    httpException = throwable
                )
                if (error != null) {
                    Result.Failure(error = error, httpCode = throwable.code())
                } else {
                    Result.Failure(error = null, httpCode = throwable.code())
                }
            }
            is IOException -> {
                Result.Failure(error = null, httpCode = IO_EXCEPTION_CODE)
            }
            else -> {
                Result.Failure(error = null, httpCode = UNEXPECTED_ERROR_CODE)
            }
        }
    }

    companion object {
        const val IO_EXCEPTION_CODE = -101
        const val UNEXPECTED_ERROR_CODE = -102
    }
}