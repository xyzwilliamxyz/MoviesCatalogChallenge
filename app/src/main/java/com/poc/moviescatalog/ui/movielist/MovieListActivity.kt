package com.poc.moviescatalog.ui.movielist

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.poc.moviescatalog.R
import com.poc.moviescatalog.domain.entities.Movie
import com.poc.moviescatalog.ui.moviedetails.MovieDetailsActivity
import com.poc.moviescatalog.utils.EndlessRecyclerViewScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject


class MovieListActivity : AppCompatActivity(), MovieListContract.View, OnMovieClickListener {

    @Inject
    lateinit var presenter: MovieListPresenter

    private lateinit var movieListAdapter: MovieListAdapter

    private var lastCall: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        supportActionBar?.title = getString(R.string.title_movies)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieListAdapter = MovieListAdapter(this, this)
        rv_movies.adapter = movieListAdapter
        rv_movies.addOnScrollListener(object: EndlessRecyclerViewScrollListener(rv_movies.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadPage(page)
            }
        })

        presenter.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.search) {
            showFilter()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onFinish()
    }

    private fun showFilter() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.message_filter))

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.filter_ok)) { _, _ -> presenter.applyFilter(input.text.toString()) }
        builder.setNegativeButton(getString(R.string.filter_cancel)) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    override fun addMovies(movies: List<Movie>) {
        movieListAdapter.addMovies(movies)
    }

    override fun setLastCall(lastCall: Long) {
        this.lastCall = lastCall
    }

    override fun showLoading(status: Boolean) {
        if (status) {
            pb_loading.visibility = View.VISIBLE
        } else {
            pb_loading.visibility = View.GONE
        }
    }

    override fun clearMovies() {
        movieListAdapter.clearMovies()
    }

    override fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error_message))

        builder.setPositiveButton(getString(R.string.error_try_again)) { _, _ -> presenter.onStart() }
        builder.setNegativeButton(getString(R.string.error_close_app)) { dialog, _ -> onSupportNavigateUp() }
        builder.show()
    }

    override fun openMovie(movie: Movie) {
        if (movie.title.equals(getString(R.string.movie_without_data))) {
            Snackbar.make(rv_movies, getString(R.string.no_data_message), Snackbar.LENGTH_LONG).show()
        } else {
            startActivity(MovieDetailsActivity.getIntent(this, movie))
        }
    }
}
