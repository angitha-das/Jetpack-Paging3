package com.example.jetpack_paging3.api

import com.example.jetpack_paging3.model.PixabayResponse
import com.example.jetpack_paging3.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var apiService: ApiService

    init {

        val logger = HttpLoggingInterceptor() //For API Logging
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService =  retrofit.create(ApiService::class.java)
    }

    companion object {
        private val apiClient: ApiClient = ApiClient()
        @Synchronized
        fun getInstance(): ApiClient {
            return apiClient
        }
    }

    suspend fun getAllImages(key:String, lastRequestedPage:Int, limit:Int, imageType: String): PixabayResponse =
        apiService.getImages(key,lastRequestedPage,limit,imageType)

}