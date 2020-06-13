package com.example.jetpack_paging3.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_paging3.R
import com.example.jetpack_paging3.model.Repo

/**
 * Adapter for the list of public github repositories.
 */
class RepoListingAdapter(private val items: ArrayList<Repo>, private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return RepoListingViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_repo, parent, false))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = items[holder.adapterPosition]
        val childHolder = holder as? RepoListingViewHolder
        if (childHolder != null) {
            holder.bind(repoItem)
        }
    }
}