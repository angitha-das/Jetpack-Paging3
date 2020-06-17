package com.example.jetpack_paging3.data

import com.example.jetpack_paging3.api.ApiClient
import com.example.jetpack_paging3.api.ApiService
import com.example.jetpack_paging3.model.Failure
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.model.ImageResult
import com.example.jetpack_paging3.model.Success

class PixabayRepository(private val service: ApiService)  {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val PAGE_SIZE = 20
    }

    private var hitResults: ArrayList<Hit> = ArrayList()

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = STARTING_PAGE_INDEX

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun getAllRepos(): List<Hit> = hitResults

    fun getAllPublicRepos(): ImageResult<List<Hit>> {
        lastRequestedPage = 1
        return when (val result = ApiClient.getInstance().getAllRepos(lastRequestedPage, PAGE_SIZE)){

            is Success -> {
                result.contents.hits.let {
                    hitResults = it as ArrayList<Hit>
                    ImageResult(contents = it)
                }
            }
            is Failure -> {
                ImageResult(result.errorCode, result.throwable)
            }
        }
    }

    private fun requestData(): Boolean {
        isRequestInProgress = true
        var successful = false
        successful = true
        isRequestInProgress = false
        return successful
    }

    fun requestMore() {
        if (isRequestInProgress) return
        val successful = requestData()
        if (successful) {
            lastRequestedPage++
        }
    }

    fun retry() {
        if (isRequestInProgress) return
        requestData()
    }
}