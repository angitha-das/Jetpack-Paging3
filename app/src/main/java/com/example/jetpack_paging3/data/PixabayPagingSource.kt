package com.example.jetpack_paging3.data

import androidx.paging.PagingSource
import com.example.jetpack_paging3.api.ApiClient
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.util.API_KEY
import com.example.jetpack_paging3.util.IMAGE_TYPE
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class PixabayPagingSource(private val apiClient: ApiClient): PagingSource<Int, Hit>()  {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiClient.getAllImages(API_KEY, position, params.loadSize, IMAGE_TYPE)
            val hits = response.hits
            LoadResult.Page(
                data = hits,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (hits.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
                val exe = Exception("Something went wrong.")
                LoadResult.Error(exe)
        } catch (exception: HttpException) {
            if(exception.code() == 400){
                val exe = Exception("Bad Request")
                LoadResult.Error(exe)
            }else {
                LoadResult.Error(exception)
            }
        }
    }
}