package com.example.jetpack_paging3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.jetpack_paging3.R
import com.example.jetpack_paging3.databinding.FragmentListingBinding
import com.example.jetpack_paging3.ui.adapters.ListingAdapter
import com.example.jetpack_paging3.ui.adapters.ListingLoadStateAdapter
import com.example.jetpack_paging3.util.InjectorUtils
import com.example.jetpack_paging3.viewmodel.ListingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListingFragment : Fragment() {

    private lateinit var adapter: ListingAdapter
    private lateinit var binding: FragmentListingBinding

    private var job: Job? = null

    private val viewModel: ListingViewModel by viewModels {
        InjectorUtils.provideListingViewModelFactory(this)
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingBinding.inflate(inflater, container, false)
        adapter = ListingAdapter()

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.repoList.addItemDecoration(decoration)

        initAdapter()
        subscribeUi()
        return binding.root
    }

    private fun initAdapter() {
        binding.repoList.adapter = adapter
        binding.repoList.adapter = adapter.withLoadStateFooter(
            footer = ListingLoadStateAdapter { adapter.retry() }
        )
    }

    @ExperimentalCoroutinesApi
    private fun subscribeUi() {
        // Make sure we cancel the previous job before creating a new one
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getImages().collectLatest {
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading){ //Refresh -API call from start
                binding.apply {
                    isLoading = true
                }
            }else if(loadState.append is LoadState.Loading || loadState.prepend is LoadState.Loading ){
                // Pagination - append: scrolled up, prepend: scroll down
                binding.apply {
                    isLoading = false
                }
            }else {
                binding.apply {
                    isLoading = false
                }
                val errorState = when {
                    loadState.append is LoadState.Error -> null
                    loadState.prepend is LoadState.Error ->  null
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    binding.apply {
                        message = it.error.toString()
                    }
                }
            }
        }
    }

}



