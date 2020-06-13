package com.example.jetpack_paging3.model

import java.lang.Exception

/**
 * RepoSearchResult contains List<Repo> holding query data,
 * and a String of network error state.
 */
sealed class RepoListingResult {
    data class Success(val data: List<Repo>) : RepoListingResult()
    data class Error(val error: Exception) : RepoListingResult()
}
