package com.example.iainteracitvemovies.domain.entities.base

import java.io.Serializable

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
data class ErrorResponseUI(
    val error: String?,
    val error_description: String?
): Serializable