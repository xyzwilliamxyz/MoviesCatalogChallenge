package com.poc.moviescatalog.ui.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.poc.moviescatalog.R
import com.poc.moviescatalog.config.glide.GlideApp
import com.poc.moviescatalog.domain.entities.Movie
import com.poc.moviescatalog.utils.Constants
import com.poc.moviescatalog.utils.DateUtils
import com.poc.moviescatalog.utils.getGenreStringList
import com.poc.moviescatalog.utils.getLanguageStringList
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews(intent.getParcelableExtra(ARGUMENT_MOVIE))
    }

    private fun initViews(movie: Movie) {

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        GlideApp.with(this)
            .load(Constants.IMAGE_BASE_URL + movie.poster)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_notfound)
            .into(iv_cover)
        tv_title_value.text = movie.title
        tv_overview_value.text = movie.overview
        tv_release_date_value.text = DateUtils.getFormattedDate(movie.releaseDate)
        tv_genres_value.text = movie.genres.getGenreStringList()
        tv_languages_value.text = movie.languages?.getLanguageStringList()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {

        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        fun getIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(ARGUMENT_MOVIE, movie)
            return intent
        }
    }
}