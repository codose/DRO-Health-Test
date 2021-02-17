package com.drohealth.pharmacy.views.ui.store

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.FragmentStoreBinding
import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.utils.ApiResponse
import com.drohealth.pharmacy.utils.hide
import com.drohealth.pharmacy.utils.show
import com.drohealth.pharmacy.utils.toast
import com.drohealth.pharmacy.views.adapter.ProductClickListener
import com.drohealth.pharmacy.views.adapter.ProductRecyclerAdapter
import com.drohealth.pharmacy.views.viemodel.StoreViewModel
import timber.log.Timber


class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    private lateinit var productAdapter : ProductRecyclerAdapter

    private lateinit var gridLayoutManager : GridLayoutManager

    private val viewModel by viewModels<StoreViewModel>()

    private var ascending = true
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

        gridLayoutManager = GridLayoutManager(requireContext(), 2)

        productAdapter = ProductRecyclerAdapter(requireContext(), ProductClickListener {
            findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToSingleStoreFragment(it))
        })
        binding.productRv.layoutManager = gridLayoutManager

        binding.productRv.adapter = productAdapter

        binding.bagCard.setOnClickListener {
            findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToBagFragment())
        }

        binding.searchButton.setOnClickListener {
            if(binding.searchLayout.isVisible){
                binding.searchLayout.hide()
            }else{
                binding.searchLayout.show()
            }
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.products.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.show()

                }
                is ApiResponse.Success -> {
                    binding.progressBar.hide()
                    val data = it.data
                    setProduct(data)
                    setSearchFunction(data)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.hide()
                    requireContext().toast(it.message)
                }
            }
        })

        viewModel.cartCount.observe(viewLifecycleOwner, {
            binding.bagCount.text = it.toString()
        })
    }

    private fun setSearchFunction(data : List<Product>) {
        binding.searchEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s == null){
                    setProduct(data)
                }else{
                    val newList = data.filter { pr->
                        pr.name.contains(s.toString(),true)
                    }
                    setProduct(newList)
                }


            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setSortFunctionality(data : List<Product>) {
        binding.sortButton.setOnClickListener {
            if(ascending){
                val sorted = data.sortedBy { item ->
                    item.name
                }
                ascending = false
                setProduct(sorted)
            }else{
                val sorted = data.sortedByDescending { item ->
                    item.name
                }
                ascending = true
                setProduct(sorted)
            }
        }
    }

    private fun setProduct(data :List<Product>) {
        binding.itemCount.text = "${data.size} Item(s)"
        productAdapter.submitList(data)
        setSortFunctionality(data)
    }

}