package com.example.jetpack_paging3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_paging3.R
import com.example.jetpack_paging3.model.Hit

/**
 * Adapter for the list of public github repositories.
 */
class ListingAdapter(private var items: ArrayList<Hit> = ArrayList()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_hit, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repo = items[holder.adapterPosition]
        (holder as ListingViewHolder).bind(repo)
    }

    fun supplyData(hits: ArrayList<Hit>) {
        items = hits
        notifyDataSetChanged()
    }
}