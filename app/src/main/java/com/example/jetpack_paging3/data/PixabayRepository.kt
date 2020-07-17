package com.example.jetpack_paging3.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpack_paging3.api.ApiClient
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.util.STARTING_PAGE_INDEX
import kotlinx.coroutines.flow.Flow

class PixabayRepository(private val apiClient: ApiClient)  {

    companion object {
        private const val PAGE_SIZE = 20
    }

    /*
    fun getImagesResultStream(): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { PixabayPagingSource(apiClient) }
        ).flow
    }
    */

    fun getImagesResultStream(): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE,prefetchDistance=1),
            initialKey = STARTING_PAGE_INDEX,
            pagingSourceFactory = { PixabayPagingSource(apiClient) }
        ).flow
    }
}