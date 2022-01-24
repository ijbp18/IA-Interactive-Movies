package com.example.iainteracitvemovies.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iainteracitvemovies.databinding.ItemMovieListBinding
import com.example.iainteracitvemovies.domain.entities.MediaUI
import com.example.iainteracitvemovies.domain.entities.MovieUI
import com.example.iainteracitvemovies.presentation.utils.loadImage

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
class MoviesAdapter(private val listener: ItemSelectedListener) :
    RecyclerView.Adapter<MoviesAdapter.ShowNewsViewHolder>() {

    private var movieList = listOf<MovieUI>()
    private lateinit var posterRouteUrlBase: String
    private lateinit var binding: ItemMovieListBinding
    private var posterRouteUrlEndpoint = ""

    interface ItemSelectedListener {
        fun onMovieSelected(movie: MovieUI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowNewsViewHolder {
        binding = ItemMovieListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowNewsViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ShowNewsViewHolder, position: Int) =
        holder.bind(movieList[position])

    fun removeAll() {
        movieList = emptyList()
        notifyDataSetChanged()
    }

    fun setData(movies: List<MovieUI>, posterUrl: String) {
        movieList = movies
        posterRouteUrlBase = posterUrl
        notifyDataSetChanged()
    }


    inner class ShowNewsViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUI) = with(binding) {
            if (posterRouteUrlBase.isNotEmpty()) {
                getImageEndpoint(movie.media)
                ivImageMovie.loadImage(posterRouteUrlBase+posterRouteUrlEndpoint)
            }
            txtMovieName.text = movie.name

            root.setOnClickListener {
                listener.onMovieSelected(movie)
            }
        }
    }

    private fun getImageEndpoint(media: ArrayList<MediaUI>) {
        media.let { mediaList ->
            mediaList.forEach { item ->
                if (item.code == MoviesFragment.IMAGE_ROUTE_KEY) {
                    posterRouteUrlEndpoint = item.resource
                }
            }
        }
    }

}