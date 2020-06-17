package com.example.jetpack_paging3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_paging3.R
import androidx.databinding.DataBindingUtil.setContentView
import com.example.jetpack_paging3.databinding.ActivityListingBinding

class ListingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityListingBinding>(this, R.layout.activity_listing)
    }
}