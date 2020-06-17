package com.example.jetpack_paging3.api

import com.example.jetpack_paging3.model.Failure
import com.example.jetpack_paging3.model.PixabayResponse
import com.example.jetpack_paging3.model.Result
import com.example.jetpack_paging3.model.Success
import com.example.jetpack_paging3.util.API_KEY
import com.example.jetpack_paging3.util.BASE_URL
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var apiService: ApiService

    init {
        apiService = create()
    }

    companion object {

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }

        private val apiClient: ApiClient = ApiClient()
        @Synchronized
        fun getInstance(): ApiClient {
            return apiClient
        }
    }


    private fun <T : Any> Call<T>.callWithExceptionHandling(): Result<T> {

        var responseCode = 101
        var responseMsg = "Unknown error"

        return try{
            val response = execute()
            if (response.isSuccessful && response.body() != null) {

                Success(response.body() as T)

            } else {
                responseCode = response.code()
                responseMsg = response.message()

                Failure(responseCode, Throwable("$responseCode: $responseMsg"))
            }
        }
        catch (e: Exception){
            if(e is JsonSyntaxException || e is MalformedJsonException){
                responseCode = 102
                responseMsg = "Invalid server response."
            }
            Failure(responseCode, Throwable("$responseCode: $responseMsg \n\n[Error Details]\n${e.localizedMessage}"))
        }

    }

    fun getAllRepos(lastRequestedPage:Int,limit:Int): Result<PixabayResponse> =
        apiService.getImages(API_KEY,lastRequestedPage,limit,"photo").callWithExceptionHandling()

}