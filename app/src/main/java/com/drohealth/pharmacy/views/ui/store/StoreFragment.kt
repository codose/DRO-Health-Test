package com.drohealth.pharmacy.views.ui.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.FragmentStoreBinding
import com.drohealth.pharmacy.utils.hide
import com.drohealth.pharmacy.utils.show
import com.drohealth.pharmacy.views.adapter.ProductClickListener
import com.drohealth.pharmacy.views.adapter.ProductRecyclerAdapter


class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    private lateinit var productAdapter : ProductRecyclerAdapter

    private lateinit var gridLayoutManager : GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductRecyclerAdapter(requireContext(), ProductClickListener {
            findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToSingleStoreFragment())
        })

        gridLayoutManager = GridLayoutManager(requireContext(), 2)

        binding.productRv.layoutManager = gridLayoutManager

        binding.productRv.adapter = productAdapter

        binding.bagCard.setOnClickListener {
            findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToBagFragment())
        }

        binding.searchButton.setOnClickListener {
            if(binding.searchLayout.isVisible){
                binding.searchLayout.show()
            }else{
                binding.searchLayout.hide()
            }
        }
        getDummyList()
    }

    private fun getDummyList() {
        val list = arrayListOf<String>()
        for(i in 1..10){
            list.add("Item $i")
        }
        productAdapter.submitList(list)
    }

}