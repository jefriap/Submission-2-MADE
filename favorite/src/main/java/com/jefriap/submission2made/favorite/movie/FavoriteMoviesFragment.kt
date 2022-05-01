package com.jefriap.submission2made.favorite.movie

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jefriap.submission1made.favorite.databinding.FragmentFavoriteMoviesBinding
import com.jefriap.submission2made.core.domain.model.MovieModel
import com.jefriap.submission2made.core.ui.movie.MovieRvAdapter
import com.jefriap.submission2made.core.utils.SortUtils
import com.jefriap.submission2made.favorite.FavoriteViewModel
import com.jefriap.submission2made.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding

    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var sort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentOrientation = resources.configuration.orientation

        _binding?.rvFavMovies?.apply {
            layoutManager = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(context, 4)
            } else {
                GridLayoutManager(context, 2)
            }
            setHasFixedSize(true)
            this.adapter = adapter

            sort = SortUtils.TITLE
            getList(sort)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.jefriap.submission2made.R.id.action_title -> sort = SortUtils.TITLE
            com.jefriap.submission2made.R.id.action_rating -> sort = SortUtils.RATING
            com.jefriap.submission2made.R.id.action_newest -> sort = SortUtils.NEWEST
            com.jefriap.submission2made.R.id.action_random -> sort = SortUtils.RANDOM
        }
        getList(sort)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun getList(sort: String) {
        binding?.loading?.visibility = View.VISIBLE
        viewModel.getFavoriteMovies(sort).observe(viewLifecycleOwner) { movie ->
            if (!movie.isNullOrEmpty()) {
                Log.i("DATA_MOVIE_FAVORITE", "Data: $movie")
                binding?.loading?.visibility = View.GONE
                binding?.rvFavMovies?.visibility = View.VISIBLE
                binding?.lottieNoData?.visibility = View.GONE
                adapter(movie)
            } else {
                Log.v("DATA_MOVIE_FAVORITE", "Data: $movie")
                binding?.loading?.visibility = View.GONE
                binding?.rvFavMovies?.visibility = View.GONE
                binding?.lottieNoData?.visibility = View.VISIBLE
            }
        }
    }

    private fun adapter(list: List<MovieModel>) {
        val adapter = MovieRvAdapter(requireContext())
        adapter.setData(list)
        _binding?.rvFavMovies?.adapter = adapter

        adapter.onItemClick = { movieId ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, movieId)
            intent.putExtra(DetailActivity.TYPE, "movie")
            startActivity(intent)
        }
    }

}