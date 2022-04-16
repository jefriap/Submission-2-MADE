package com.jefriap.submission2made.core.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jefriap.submission2made.core.R
import com.jefriap.submission2made.core.databinding.ItemRowBinding
import com.jefriap.submission2made.core.domain.model.TvShowModel
import com.jefriap.submission2made.core.utils.imageLoad
import com.jefriap.submission2made.core.utils.loadImage

class TvShowRvAdapter(private val dataTvShows: List<TvShowModel>, private val context: Context) : RecyclerView.Adapter<TvShowRvAdapter.ListViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataTvShows[position]
        with(holder.binding) {
            with(data) {
                //image
                if (poster.isNullOrEmpty()) {
                    imgMovie.imageLoad(R.drawable.img_not_found)
                } else imgMovie.loadImage("https://image.tmdb.org/t/p/w500/$poster")
                //title
                tvJudulFilm.text = name
                //genre
                tvGenreFilm.text = overview
                //release date
                if (releaseDate == "") {
                    tvDate.textSize = 9f
                    tvDate.text = context.getString(R.string.blm_tersedia)
                } else tvDate.text = releaseDate
                //rating
                if (rating == 0.0) {
                    tvRating.textSize = 9f
                    tvRating.text = context.getString(R.string.blm_tersedia)
                } else tvRating.text = rating.toString()
            }
        }
    }

    override fun getItemCount(): Int = dataTvShows.size

    inner class ListViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        //when item clicked
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(dataTvShows[adapterPosition].tvShowId)
            }
        }
    }
}