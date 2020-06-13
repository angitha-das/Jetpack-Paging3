package com.example.jetpack_paging3.api

import com.example.jetpack_paging3.model.Repo
import com.google.gson.annotations.SerializedName

/**
 * Data class to hold repo responses from public repo API calls.
 */
data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)
