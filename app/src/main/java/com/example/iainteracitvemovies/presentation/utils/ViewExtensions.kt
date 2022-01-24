package com.example.iainteracitvemovies.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.iainteracitvemovies.R

/**
 * Created by Joao Betancourth on 23,enero,2022
 */

fun ImageView.loadImage(urlToImage: String?) {

    Glide.with(context)
        .load(urlToImage)
        .centerCrop()
        .placeholder(R.drawable.ic_image_placeholder)
        .into(this)
}
