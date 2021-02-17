package com.drohealth.pharmacy.views.ui.bag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.DialogAddBagDialogBinding
import com.drohealth.pharmacy.databinding.FragmentBagBinding
import com.drohealth.pharmacy.databinding.FragmentStoreBinding
import com.drohealth.pharmacy.utils.CartOp
import com.drohealth.pharmacy.views.adapter.BagClickListener
import com.drohealth.pharmacy.views.adapter.BagRecyclerAdapter
import com.drohealth.pharmacy.views.adapter.ProductClickListener
import com.drohealth.pharmacy.views.adapter.ProductRecyclerAdapter
import com.drohealth.pharmacy.views.ui.store.StoreFragmentDirections
import com.drohealth.pharmacy.views.viemodel.StoreViewModel
import timber.log.Timber

class BagFragment : Fragment() {

    private lateinit var binding: FragmentBagBinding

    private val viewModel by viewModels<StoreViewModel>()

    private lateinit var bagAdapter : BagRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBagBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bagAdapter = BagRecyclerAdapter(requireContext(), BagClickListener { item, operation ->
            when (operation) {
                CartOp.ALTER -> {
                    viewModel.addToCart(item)
                }
                CartOp.REMOVE -> {
                    Timber.v("Delete Clicked")
                    viewModel.removeFromCart(item)
                }
            }
        })


        //Setting Bag Count
        viewModel.cartCount.observe(viewLifecycleOwner, {
            binding.bagCount.text = it.toString()
        })

        binding.bagRv.adapter = bagAdapter


        viewModel.cartItems.observe(viewLifecycleOwner, {
            var price = 0
            bagAdapter.submitList(it)
            it.forEach { pr ->
                price += (pr.count * pr.price)
            }
            binding.totalPrice.text = "${getString(R.string.naira)}$price"
        })
    }


}