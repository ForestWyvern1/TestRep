package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapi.Case.genre
import com.bumptech.glide.Glide
import com.example.testapi.Case.item
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        release_date_movie.text = item!!.release_date

        overview_text.text = item!!.overview

        Glide.with(movie_image)
            .load("https://image.tmdb.org/t/p/w1280" + item!!.poster_path)
            .into(movie_image)

        text_movie.text = item!!.title

        if (item!!.video) video_movie.text = "Enable"
        else video_movie.text = "Disable"

        for (i in genre!!){
            if (item!!.genre_ids.contains(i.id))
            genres_movie.text = (genres_movie.text.toString() + i.name + " ").capitalize()
        }

        average_vote.text = average_vote.text.toString() + item!!.vote_average.toString()

    }
}