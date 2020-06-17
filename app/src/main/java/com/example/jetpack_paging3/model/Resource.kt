package com.example.jetpack_paging3.model

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    fun isSuccess() = status == Status.SUCCESS

    companion object {
        fun <T> success(data: T?): Resource<T> =Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String): Resource<T> = Resource(Status.ERROR, null, msg)
        fun <T> loading(): Resource<T> = Resource(Status.PROGRESSING, null, null)
        fun <T> empty(): Resource<T> = Resource(Status.EMPTY_RESPONSE, null, null)
    }
}

enum class Status{
    EMPTY_RESPONSE,
    PROGRESSING,
    SUCCESS,
    ERROR,
    SWIPE_RELOADING
}