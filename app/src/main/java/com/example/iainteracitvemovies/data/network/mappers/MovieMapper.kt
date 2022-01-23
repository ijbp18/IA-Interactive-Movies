package com.example.iainteracitvemovies.data.network.mappers

import com.example.iainteracitvemovies.data.network.model.Cast
import com.example.iainteracitvemovies.data.network.model.Media
import com.example.iainteracitvemovies.data.network.model.Movies
import com.example.iainteracitvemovies.data.network.model.MoviesResponse
import com.example.iainteracitvemovies.domain.entities.CastUI
import com.example.iainteracitvemovies.domain.entities.MediaUI
import com.example.iainteracitvemovies.domain.entities.MovieContentUI
import com.example.iainteracitvemovies.domain.entities.MovieUI

/**
 * Created by Joao Betancourth on 23,enero,2022
 */


fun mapFromEntityMovieList(entityList: MoviesResponse): MovieUI {
    return MovieUI(
        ArrayList(entityList.movies.map { toMovieContentUI(it) })
    )
}

fun toMovieContentUI(entity: Movies): MovieContentUI {
    return MovieContentUI(
        entity.rating,
        ArrayList(entity.media.map { it.toMediaUI() }),
        ArrayList(entity.cast.map { it.toCastUI() }),
        entity.position,
        entity.genre,
        entity.synopsis,
        entity.length,
        entity.release_date,
        entity.distributor,
        entity.id,
        entity.name,
        entity.code,
        entity.original_name
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