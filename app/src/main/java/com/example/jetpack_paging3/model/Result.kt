package com.example.jetpack_paging3.model

sealed class Result<out T : Any>

data class Success<out T : Any>(val contents: T) : Result<T>()
data class Failure(val errorCode: Int, val throwable: Throwable) : Result<Nothing>()

