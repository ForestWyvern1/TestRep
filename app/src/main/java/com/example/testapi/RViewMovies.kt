package com.example.testapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapi.Case.item
import kotlinx.android.synthetic.main.rv_view_movies.view.*

class RViewMovies(val list: List<Result>, val onClickListener: OnClickListener, val context: Context, val genreList: List<Genre>) : RecyclerView.Adapter<RViewMovies.RView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RView {
        return RView(LayoutInflater.from(parent.context).inflate(R.layout.rv_view_movies, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RView, position: Int) {
        Glide.with(holder.itemView.image_view_movies)
            .load("https://image.tmdb.org/t/p/w1280" + list[position].poster_path)
            .into(holder.itemView.image_view_movies)

        holder.itemView.image_view_movies.setOnClickListener {
            item = list[position]
            onClickListener.click(list[position])
        }

    }
    class RView(view: View) : RecyclerView.ViewHolder(view)



    interface OnClickListener{
        fun click(data: Result)
    }
}