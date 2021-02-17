package com.drohealth.pharmacy.views.ui.single

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.FragmentSingleStoreBinding
import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.views.ui.bag.BagFragment
import com.drohealth.pharmacy.views.ui.dialog.SuccessDialogFragment
import com.drohealth.pharmacy.views.viemodel.StoreViewModel


class SingleStoreFragment : Fragment() {

    private val viewModel by viewModels<StoreViewModel>()
    private lateinit var binding : FragmentSingleStoreBinding
    private var count = 1 //Initial Count of Product
    private lateinit var product : Product
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = SingleStoreFragmentArgs.fromBundle(requireArguments()).product

        initView()

        setObservers()

    }

    private fun setObservers() {
        viewModel.cartCount.observe(viewLifecycleOwner, {
            binding.bagCount.text = it.toString()
        })

    }

    private fun initView() {
        setCount()

        //Add Count to the product
        binding.bagAdd.setOnClickListener {
            if(count in 1..4){
                count+=1
                setCount()
            }
        }

        //Remove count from the product
        binding.bagMinus.setOnClickListener {
            if(count != 1){
                count-=1
                setCount()
            }
        }

        binding.constituents.text = product.constituents
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.dispensedIn.text = product.dispensedIn
        binding.packSize.text = product.size
        binding.productId.text = product.productId
        binding.productDescription.text = product.description
        binding.productName.text = product.name
        binding.productPrice.text = product.price.toString()
        binding.productVendor.text = product.vendor
        binding.productType.text = product.type
        Glide.with(requireContext())
                .load(product.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.productImage)

        binding.addToBagBtn.setOnClickListener {
            viewModel.addToCart(product)
            openSuccessDialog()
        }

        binding.bagCount.setOnClickListener {
            findNavController().navigate(SingleStoreFragmentDirections.actionSingleStoreFragmentToBagFragment())
        }
    }

    private fun setCount(){
        binding.bagAddCount.text = count.toString()
        binding.productPrice.text = "${count * product.price}"
        product.count = count
    }

    private fun openSuccessDialog() {
        val dialogFragment = SuccessDialogFragment.newInstance(product.name)
        dialogFragment.isCancelable = true
        dialogFragment.setTargetFragment(this, 700)
        dialogFragment.show(requireFragmentManager().beginTransaction(),"dialog")
    }

}