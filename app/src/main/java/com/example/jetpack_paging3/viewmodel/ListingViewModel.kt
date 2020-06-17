package com.example.jetpack_paging3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_paging3.data.PixabayRepository
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.model.Resource
import kotlinx.coroutines.*

/**
 * ViewModel for the RepoListingFragment screen.
 * The ViewModel works with the [PixabayRepository] to get the public repo list data.
 */
class ListingViewModel internal constructor(
    private val pixabayRepository: PixabayRepository
) : ViewModel() {
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private var hitList: MutableList<Hit>
    private val repoLiveData = MutableLiveData<Resource<List<Hit>>>()
    init {
        getAllPublicRepositories()
        hitList = pixabayRepository.getAllRepos().toMutableList()
    }

    fun getAllPublicRepositories(): MutableLiveData<Resource<List<Hit>>> {
        if(repoLiveData.value == null){
            repoLiveData.value = Resource.loading()

            uiScope.launch {
                val d = async(Dispatchers.IO) {
                    pixabayRepository.getAllPublicRepos()
                }
                val result = d.await()

                if (result.isSuccess()) {
                    hitList.clear()
                    hitList = ArrayList(pixabayRepository.getAllRepos())

                    if (hitList.isEmpty()) {
                        repoLiveData.value = Resource.empty()
                    } else {
                        repoLiveData.value = Resource.success(hitList)
                    }

                } else {
                    repoLiveData.value = Resource.error(result.error?.localizedMessage ?: "Unknown error")
                }

            }
        }

        return repoLiveData
    }
}