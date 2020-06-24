package com.example.jetpack_paging3.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_paging3.model.Hit

/**
 * Adapter for the list of public github repositories.
 */
class ListingAdapter: PagingDataAdapter<Hit, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListingViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hitItem = getItem(position)
        if (hitItem != null) {
            (holder as ListingViewHolder).bind(hitItem)
        }
    }
}