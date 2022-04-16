package com.jefriap.submission2made.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.jefriap.submission2made.core.domain.model.MovieModel

class DiffUtils(private val oldList: List<MovieModel>, private val newList: List<MovieModel>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].movieId == newList[newItemPosition].movieId
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}