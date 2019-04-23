package com.poc.moviescatalog.ui.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.poc.moviescatalog.R
import com.poc.moviescatalog.config.glide.GlideApp
import com.poc.moviescatalog.domain.entities.Movie
import com.poc.moviescatalog.utils.Constants
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieListAdapter(private val context: Context, private val listener: OnMovieClickListener)
    : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

    private var isTablet: Boolean = context.resources.getBoolean(R.bool.isTablet)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent,false)
        return MovieViewHolder(view).bindEvent()
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun clearMovies() {
        this.movies.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movies[position].title
        holder.subtitle.text = movies[position].overview

        // avoid wasted request
        if (movies[position].poster == null) {
            GlideApp.with(context)
                .load(R.drawable.ic_notfound)
                .into(holder.cover)
        } else {
            if (isTablet) {
                GlideApp.with(context)
                    .load(Constants.IMAGE_BASE_URL + movies[position].poster)
                    .placeholder(R.drawable.tablet_placeholder)
                    .error(R.drawable.ic_notfound)
                    .into(holder.cover)
            } else {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = STROKE_WIDTH
                circularProgressDrawable.centerRadius = CENTER_RADIUS
                circularProgressDrawable.start()

                GlideApp.with(context)
                    .load(Constants.IMAGE_BASE_URL + movies[position].poster)
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_notfound)
                    .into(holder.cover)
            }
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_title_label
        val subtitle: TextView = view.tv_subtitle
        val cover: ImageView = view.iv_cover
        private val item: ViewGroup = view.cl_item_movie

        fun bindEvent(): MovieViewHolder {
            item.setOnClickListener {
                listener.openMovie(movies[adapterPosition])
            }
            return this
        }
    }

    companion object {
        private const val STROKE_WIDTH = 5f
        private const val CENTER_RADIUS = 30f
    }
}