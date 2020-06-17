package com.example.jetpack_paging3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jetpack_paging3.databinding.FragmentListingBinding
import com.example.jetpack_paging3.model.Hit
import com.example.jetpack_paging3.util.InjectorUtils
import com.example.jetpack_paging3.viewmodel.ListingViewModel

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding

    private val viewModel: ListingViewModel by viewModels {
        InjectorUtils.provideListingViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingBinding.inflate(inflater, container, false)
        val adapter = ListingAdapter()
        binding.repoList.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: ListingAdapter) {
        viewModel.getAllPublicRepositories().observe(viewLifecycleOwner, Observer {
            it?.let { res ->
                if (res.isSuccess()) {
                    res.data?.let { repos ->  adapter.supplyData(repos as ArrayList<Hit>) }
                }
//                processStatus(res)
            }
        })
    }

}



