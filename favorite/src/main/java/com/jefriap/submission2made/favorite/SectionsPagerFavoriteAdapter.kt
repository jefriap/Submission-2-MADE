package com.jefriap.submission2made.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jefriap.submission2made.favorite.movie.FavoriteMoviesFragment
import com.jefriap.submission2made.favorite.tvshow.FavoriteTvShowsFragment

class SectionsPagerFavoriteAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FavoriteMoviesFragment()
            }
            1 -> {
                fragment = FavoriteTvShowsFragment()
            }
        }
        return fragment as Fragment
    }
}