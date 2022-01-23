package com.example.iainteracitvemovies.domain.entities

/**
 * Created by Joao Betancourth on 23,enero,2022
 */

data class MovieUI(
    val movies: ArrayList<MovieContentUI>
)

data class MovieContentUI(
    val rating: String,
    val media: ArrayList<MediaUI>,
    val cast: ArrayList<CastUI>,
    val position: Int,
    val genre: String,
    val synopsis: String,
    val length: String,
    val release_date: String,
    val distributor: String,
    val id: String,
    val name: String,
    val code: String,
    val original_name: String
)

data class MediaUI(
    val resource: String,
    val type: String,
    val code: String
)

data class CastUI(
    val label: String,
    val value: ArrayList<String>
)