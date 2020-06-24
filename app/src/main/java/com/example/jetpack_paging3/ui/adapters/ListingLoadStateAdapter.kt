package com.example.jetpack_paging3.ui.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ListingLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ListingLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ListingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ListingLoadStateViewHolder {
        return ListingLoadStateViewHolder.create(parent, retry)
    }
}