package com.example.iainteracitvemovies.data.network.mappers

import com.example.iainteracitvemovies.data.network.model.*
import com.example.iainteracitvemovies.domain.entities.*

/**
 * Created by Joao Betancourth on 23,enero,2022
 */


fun MoviesResponse.toMovieUIList(): MovieInfoUI {
    return MovieInfoUI(
        ArrayList(this.movies.map { it.toMovieUI() }),
        ArrayList(this.routes.map { it.toRouteUI() })
    )
}

fun Movies.toMovieUI(): MovieUI {
    return MovieUI(
        this.rating,
        ArrayList(this.media.map { it.toMediaUI()}),
        ArrayList(this.cast.map { it.toCastUI() }),
        this.position,
        this.genre,
        this.synopsis,
        this.length,
        this.release_date,
        this.distributor,
        this.id,
        this.name,
        this.code,
        this.original_name
    )
}

fun Media.toMediaUI(): MediaUI {
    return MediaUI(
        this.resource,
        this.type,
        this.code
    )
}

fun Cast.toCastUI(): CastUI {
    return CastUI(
        this.label,
        this.value
    )
}

fun Routes.toRouteUI(): RouteUI {
    return RouteUI(
        this.code,
        this.sizes
    )
}
