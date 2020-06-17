package com.example.jetpack_paging3.model

data class ImageResult<out T>(val code: Int? = null, val error: Throwable? = null, val contents: T? = null){
    fun isSuccess() = error == null || code == null
}