package com.wiryatech.adsintermediate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wiryatech.adsintermediate.model.MovieModel
import com.wiryatech.adsintermediate.R
import kotlinx.android.synthetic.main.item_movie_horizontal.view.*

class MovieAdapter(private var data: List<MovieModel>,
                   private val listener: (MovieModel) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_horizontal, parent, false)
        return MovieViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position], listener, position)
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: MovieModel, listener: (MovieModel) -> Unit, position: Int) {
            with(itemView) {
                tv_title.text = data.title
                Glide.with(context)
                    .load(data.poster)
                    .into(iv_poster)

                itemView.setOnClickListener {
                    listener(data)
                }
            }
        }
    }

//    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(data: MovieModel, listener: (MovieModel) -> Unit, context: Context, position: Int) {
//            with(itemView) {
//                Glide.with(itemView.context)
//                    .load(data.title)
//                    .into(iv_poster)
//
//                tv_title.text = data.title
//
//                itemView.setOnClickListener { listener(data) }
//            }
//        }
//    }
}