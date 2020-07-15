package com.example.jetpack_paging3.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.jetpack_paging3.databinding.FragmentListingBinding
import com.example.jetpack_paging3.ui.adapters.ListingAdapter
import com.example.jetpack_paging3.ui.adapters.ListingLoadStateAdapter
import com.example.jetpack_paging3.util.InjectorUtils
import com.example.jetpack_paging3.viewmodel.ListingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class ListingFragment : Fragment() {

    private lateinit var adapter: ListingAdapter
    private lateinit var binding: FragmentListingBinding

    private var job: Job? = null

    private val listingViewModel: ListingViewModel by viewModels {
        InjectorUtils.provideListingViewModelFactory(this)
    }

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        subscribeUi()
        setRequiredListeners()
    }

    private fun initAdapter() {
        binding.repoList.adapter = adapter
        binding.repoList.adapter = adapter.withLoadStateFooter(
            footer = ListingLoadStateAdapter { adapter.retry() }
        )
    }

    private fun subscribeUi() {
        // Make sure we cancel the previous job before creating a new one
        job?.cancel()
        job = lifecycleScope.launch {
            listingViewModel.getImages().collectLatest {
                swipeToRefresh.isRefreshing = false
                adapter.submitData(it)
            }
        }
    }

    private fun setRequiredListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            swipeToRefresh.isRefreshing = true
            adapter.refresh()
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                isLoading = loadState.refresh is LoadState.Loading && !swipeToRefresh.isRefreshing
            }
            if (loadState.refresh !is LoadState.Loading || loadState.append !is LoadState.Loading || loadState.prepend !is LoadState.Loading) {
                swipeToRefresh.isRefreshing = false
                val errorState = when {
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    loadState.append is LoadState.Error -> null
                    loadState.prepend is LoadState.Error -> null
                    else -> null
                }
                if (errorState != null) {
                    showErrorSnackBar(rootCL, errorState.error.toString())
                }
            }
        }
    }

    private fun showErrorSnackBar(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("CLOSE") {
            snackBar.dismiss();
        }
            .setActionTextColor(Color.WHITE)
        snackBar.show()
    }

}



