package com.example.jetpack_paging3.data

import com.example.jetpack_paging3.api.GithubService

class GithubRepository(private val service: GithubService)  {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}