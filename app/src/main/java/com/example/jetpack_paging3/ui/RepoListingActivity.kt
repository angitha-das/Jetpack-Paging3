package com.example.jetpack_paging3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_paging3.R

class RepoListingActivity : AppCompatActivity() {

    private lateinit var viewModel: RepoListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_listing)
    }
}