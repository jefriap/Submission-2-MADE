package com.jefriap.submission2made.ui.tvshow

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jefriap.submission2made.R
import com.jefriap.submission2made.core.data.Resource
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.ui.tvshow.TvShowRvAdapter
import com.jefriap.submission2made.core.utils.SortUtils
import com.jefriap.submission2made.databinding.FragmentTvShowBinding
import com.jefriap.submission2made.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private val viewModel: TvShowViewModel by viewModel()

    private lateinit var sort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentOrientation = resources.configuration.orientation

        binding?.rvTvShow?.apply {
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
        viewModel.getTvShows(sort).observe(viewLifecycleOwner) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding?.loading?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding?.loading?.visibility = View.GONE
                        binding?.rvTvShow?.visibility = View.VISIBLE
                        tvShow.data?.let {data ->
                            adapter(data)
                        }
                        Log.i("Resource_Success_TvShow", "Data: ${tvShow.data}")
                    }
                    is Resource.Error -> {
                        binding?.loading?.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                        Log.e("Resource_Error", "Error: ${tvShow.message}")
                    }
                }
            }
        }
    }

    private fun adapter(list: List<TvShowModel>) {
        val adapter = TvShowRvAdapter(list, requireContext())
        _binding?.rvTvShow?.adapter = adapter

        adapter.onItemClick = { tvShowId ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, tvShowId)
            intent.putExtra(DetailActivity.TYPE, "tv")
            startActivity(intent)
        }
    }

}