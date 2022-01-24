package com.example.iainteracitvemovies.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
data class MoviesResponse(
    @SerializedName("movies")
    val movies: ArrayList<Movies>,
    @SerializedName("routes")
    val routes: ArrayList<Routes>
)

data class Movies(
    @SerializedName("rating")
    val rating: String,
    @SerializedName("media")
    val media: ArrayList<Media>,
    @SerializedName("cast")
    val cast: ArrayList<Cast>,
    @SerializedName("position")
    val position: Int,
    @SerializedName("categories")
    val categories: ArrayList<String>,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("distributor")
    val distributor: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("original_name")
    val original_name: String
)

data class Media(
    @SerializedName("resource")
    val resource: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("code")
    val code: String
)

data class Cast(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: ArrayList<String>
)

data class Routes(
    @SerializedName("code")
    val code: String,
    @SerializedName("sizes")
    val sizes: Size
)

data class Size(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("x-large")
    val xlarge: String
)
