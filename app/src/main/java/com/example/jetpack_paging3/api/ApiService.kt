package com.example.jetpack_paging3.api

import com.example.jetpack_paging3.model.PixabayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     * Get list of images from pixabay.
     */
    @GET("api/")
    fun getImages(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
        @Query("image_type") imageType: String
    ): Call<PixabayResponse>
}
