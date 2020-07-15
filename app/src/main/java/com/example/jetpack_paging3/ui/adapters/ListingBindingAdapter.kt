package com.example.jetpack_paging3.ui.adapters

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.jetpack_paging3.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("inflateData")
fun inflateData(
    layout: ChipGroup,
    tag: String?
) {
    val tags = tag?.split(",")?.toTypedArray()
    layout.removeAllViews()
    tags?.forEach {
        val tagChip = Chip(layout.context)
        tagChip.text = it
        tagChip.isEnabled = false
        tagChip.setTextColor(Color.BLACK)
        tagChip.setChipCornerRadiusResource(R.dimen.chip_corner_radius)
        layout.addView(tagChip)
    }
}