package com.jefriap.submission2made.favorite.tvshow

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
import com.jefriap.submission1made.favorite.databinding.FragmentFavoriteTvShowsBinding
import com.jefriap.submission2made.R
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.ui.tvshow.TvShowRvAdapter
import com.jefriap.submission2made.core.utils.SortUtils
import com.jefriap.submission2made.favorite.FavoriteViewModel
import com.jefriap.submission2made.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
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
        _binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentOrientation = resources.configuration.orientation

        _binding?.rvFavTvShow?.apply {
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
            R.id.action_title -> sort = SortUtils.TITLE
            R.id.action_rating -> sort = SortUtils.RATING
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        getList(sort)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun getList(sort: String) {
        binding?.loading?.visibility = View.VISIBLE
        viewModel.getFavoriteTvShows(sort).observe(viewLifecycleOwner) { tvShow ->
            if (!tvShow.isNullOrEmpty()) {
                Log.i("DATA_TVSHOW_FAVORITE", "Data: $tvShow")
                binding?.loading?.visibility = View.GONE
                binding?.rvFavTvShow?.visibility = View.VISIBLE
                binding?.lottieNoData?.visibility = View.GONE
                adapter(tvShow)
            } else {
                Log.v("DATA_TVSHOW_FAVORITE", "Data: $tvShow")
                binding?.loading?.visibility = View.GONE
                binding?.rvFavTvShow?.visibility = View.GONE
                binding?.lottieNoData?.visibility = View.VISIBLE
            }
        }
    }

    private fun adapter(list: List<TvShowModel>) {
        val adapter = TvShowRvAdapter(list, requireContext())
        _binding?.rvFavTvShow?.adapter = adapter

        adapter.onItemClick = { tvShowId ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, tvShowId)
            intent.putExtra(DetailActivity.TYPE, "tv")
            startActivity(intent)
        }
    }

}