package com.example.jetpack_paging3.ui

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_paging3.databinding.ListItemHitBinding
import com.example.jetpack_paging3.model.Hit

/**
 * View Holder for a [Hit] RecyclerView list item.
 */
class ListingViewHolder(private val binding: ListItemHitBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener { view ->
            binding.dataHit?.let { hit ->
                navigateToDetail(hit.id, view)
            }
        }
    }

    fun bind(hit: Hit?) {
        binding.apply {
            dataHit = hit
            executePendingBindings()
        }
    }

    private fun navigateToDetail(hitId: Int, view: View) {
        val direction = ListingFragmentDirections
            .actionListingFragmentToDetailFragment(hitId)
        view.findNavController().navigate(direction)
    }
}
