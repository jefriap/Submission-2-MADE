package com.jefriap.submission2made.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jefriap.submission1made.favorite.R
import com.jefriap.submission1made.favorite.databinding.FragmentFavoriteBinding
import com.jefriap.submission2made.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private var mediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionsPagerFavoriteAdapter(requireActivity())
        val viewPager: ViewPager2? = binding?.viewPager
        viewPager?.adapter = sectionPagerAdapter
        viewPager?.isSaveEnabled = false

        loadKoinModules(favoriteModule)

        val tabs: TabLayout? = binding?.tabs
        if (tabs != null && viewPager != null) {
            mediator = TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TILES[position])
            }

            mediator?.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator?.detach()
        mediator = null
        binding?.viewPager?.adapter = null
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TILES = intArrayOf(
            R.string.movies,
            R.string.tv_show
        )
    }

}