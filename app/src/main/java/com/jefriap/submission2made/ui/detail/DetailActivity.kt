package com.jefriap.submission2made.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jefriap.submission2made.R
import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.databinding.ActivityDetailBinding
import com.jefriap.submission2made.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //-- Handle navigation icon press --//
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        val itemId = intent.getIntExtra(EXTRA_ID, 0)
        val typeData = intent.getStringExtra(TYPE)

        viewModel.setSelectedItem(itemId)

        when (typeData) {
            "movie" -> {
                movieObserve()
            }
            "tv" -> {
                tvShowObserve()
            }
            else -> {
                Log.e("DETAIL_ACTIVITY", "Error: Observe not found")
            }
        }

    }

    private fun movieObserve() {
        viewModel.movieItem.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.loading2.visibility = View.VISIBLE
                        binding.imgBackdrop.visibility = View.GONE
                        binding.viewDetail.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        binding.loading2.visibility = View.GONE
                        binding.imgBackdrop.visibility = View.VISIBLE
                        binding.viewDetail.visibility = View.VISIBLE
                        movie.data?.let { bindDetailMovie(it) }
                        menuItemClicked("Movie")
                    }
                    is Resource.Error -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.loading2.visibility = View.VISIBLE
                    }
                }
            } else {
                binding.imgBackdrop.visibility = View.GONE
                binding.viewDetail.visibility = View.GONE
                Toast.makeText(this, "Data Tidak Ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tvShowObserve() {
        viewModel.tvShowItem.observe(this) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.loading2.visibility = View.VISIBLE
                        binding.imgBackdrop.visibility = View.GONE
                        binding.viewDetail.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        binding.loading2.visibility = View.GONE
                        binding.imgBackdrop.visibility = View.VISIBLE
                        binding.viewDetail.visibility = View.VISIBLE
                        tvShow.data?.let { bindDetailTvShow(it) }
                        menuItemClicked("TvShow")
                    }
                    is Resource.Error -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.loading2.visibility = View.VISIBLE
                    }
                }
            } else {
                binding.imgBackdrop.visibility = View.GONE
                binding.viewDetail.visibility = View.GONE
                Toast.makeText(this, "Data Tidak Ada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindDetailMovie(data: MovieModel) {
        with(binding) {
            imgMovie.loadImage("https://image.tmdb.org/t/p/w500/" + data.poster)
            imgBackdrop.loadImage("https://image.tmdb.org/t/p/w500/" + data.backdrop)
            tvJudul.text = data.title
//            tvDurasi.text = data.runtime
            tvRilis.text = data.releaseDate
            tvScore.text = data.rating.toString()
            tvoverview.text = data.overview
            topAppBar.apply {
                favoriteState(menu.findItem(R.id.btnFav), data.favorite)
            }
        }
    }

    private fun bindDetailTvShow(data: TvShowModel) {
        with(binding) {
            imgMovie.loadImage("https://image.tmdb.org/t/p/w500/" + data.poster)
            imgBackdrop.loadImage("https://image.tmdb.org/t/p/w500/" + data.backdrop)
            tvJudul.text = data.name
//            tvDurasi.text = data.runtime
            tvRilis.text = data.releaseDate
            tvScore.text = data.rating.toString()
            tvoverview.text = data.overview
            topAppBar.apply {
                favoriteState(menu.findItem(R.id.btnFav), data.favorite)
            }
        }
    }

    private fun menuItemClicked (typeItem: String) {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btnFav -> {
                    setFavoriteItem(typeItem)
                    true
                }
                else -> true
            }
        }
    }

    private fun setFavoriteItem (typeItem: String) {
        if (typeItem == "Movie") {
            viewModel.setFavoriteMovie()
        }
        else if (typeItem == "TvShow") {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun favoriteState(menu: MenuItem, state: Boolean?) {
        menu.apply {
            icon = if (state!!) {
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    R.drawable.ic_baseline_favorite_red_24
                )
            } else {
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    R.drawable.ic_baseline_favorite_24
                )
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val TYPE = "type"
    }
}