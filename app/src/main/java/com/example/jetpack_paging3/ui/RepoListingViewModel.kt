package com.example.jetpack_paging3.ui

import com.example.jetpack_paging3.data.GithubRepository

/**
 * ViewModel for the [RepoListingActivity] screen.
 * The ViewModel works with the [GithubRepository] to get the public repo list data.
 */
class RepoListingViewModel(private val repository: GithubRepository) {
}