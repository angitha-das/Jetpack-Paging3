package com.example.jetpack_paging3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack_paging3.data.PixabayRepository
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.model.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for the RepoListingFragment screen.
 * The ViewModel works with the [PixabayRepository] to get the public repo list data.
 */
class ListingViewModel internal constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    private var currentImagesResult: Flow<PagingData<Hit>>? = null

    fun getImages(): Flow<PagingData<Hit>> {
        val lastResult = currentImagesResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Hit>> = repository.getImagesResultStream()
            .cachedIn(viewModelScope)
        currentImagesResult = newResult
        return newResult
    }
}