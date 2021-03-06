package com.example.jetpack_paging3.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_paging3.R
import com.example.jetpack_paging3.databinding.ListItemHitBinding
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.ui.fragments.ListingFragmentDirections

/**
 * View Holder for a [Hit] RecyclerView list item.
 */
class ListingViewHolder(private val binding: ListItemHitBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ListingViewHolder {
            return ListingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_hit, parent, false
                )
            )
        }
    }

    init {
        binding.setClickListener { view ->
            binding.dataHit?.let { hit ->
                navigateToDetail(hit, view)
            }
        }
    }

    fun bind(hit: Hit?) {
        binding.apply {
            dataHit = hit
            executePendingBindings()
        }
    }

    private fun navigateToDetail(hit: Hit, view: View) {
        val direction =
            ListingFragmentDirections.actionListingFragmentToDetailFragment(
                hit
            )
        view.findNavController().navigate(direction)
    }
}
