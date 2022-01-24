package com.example.iainteracitvemovies.domain.entities

import com.example.iainteracitvemovies.data.network.model.Size
import java.io.Serializable


/**
 * Created by Joao Betancourth on 23,enero,2022
 */

data class MovieInfoUI(
    val movies: ArrayList<MovieUI>,
    val routes: ArrayList<RouteUI>
)

data class MovieUI(
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
):Serializable

data class MediaUI(
    val resource: String,
    val type: String,
    val code: String
)

data class CastUI(
    val label: String,
    val value: ArrayList<String>
)

data class RouteUI(
    val code: String,
    val sizes: Size
)