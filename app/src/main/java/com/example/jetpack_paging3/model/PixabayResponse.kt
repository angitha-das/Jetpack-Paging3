package com.example.jetpack_paging3.model

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    @SerializedName("total") val total: Int = 0,
    @SerializedName("totalHits") val totalHits: Int = 0,
    @SerializedName("hits") val hits: List<Hit> = emptyList()
)

data class Hit(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("pageURL") val pageURL: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("tags") val tags: String?,
    @field:SerializedName("previewURL") val previewURL: String,
    @field:SerializedName("previewWidth") val previewWidth: Int,
    @field:SerializedName("previewHeight") val previewHeight: Int,
    @field:SerializedName("webformatURL") val webformatURL: String,
    @field:SerializedName("webformatWidth") val webformatWidth: Int,
    @field:SerializedName("webformatHeight") val webformatHeight: Int,
    @field:SerializedName("largeImageURL") val largeImageURL: String,
    @field:SerializedName("imageWidth") val imageWidth: Int,
    @field:SerializedName("imageHeight") val imageHeight: Int,
    @field:SerializedName("imageSize") val imageSize: Int,
    @field:SerializedName("views") val views: Int,
    @field:SerializedName("downloads") val downloads: Int,
    @field:SerializedName("favorites") val favorites: Int,
    @field:SerializedName("likes") val likes: Int,
    @field:SerializedName("user_id") val userId: Int,
    @field:SerializedName("user") val user: String,
    @field:SerializedName("userImageURL") val userImageURL: String?
)

