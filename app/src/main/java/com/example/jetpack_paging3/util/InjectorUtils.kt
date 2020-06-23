package com.example.jetpack_paging3.util

import androidx.fragment.app.Fragment
import com.example.jetpack_paging3.api.ApiClient
import com.example.jetpack_paging3.data.PixabayRepository
import com.example.jetpack_paging3.viewmodel.ListingViewModelFactory

object InjectorUtils {
    /**
     * Creates an instance of [PixabayRepository]
     */
    private fun providePixabayRepository(): PixabayRepository {
        return PixabayRepository(ApiClient.getInstance())
    }

    fun provideListingViewModelFactory(
        fragment: Fragment
    ): ListingViewModelFactory {
        return ListingViewModelFactory(providePixabayRepository(),fragment)
    }

}