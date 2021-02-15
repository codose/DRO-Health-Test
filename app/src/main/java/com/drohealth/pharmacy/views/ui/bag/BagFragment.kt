package com.drohealth.pharmacy.views.ui.bag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.DialogAddBagDialogBinding
import com.drohealth.pharmacy.databinding.FragmentBagBinding
import com.drohealth.pharmacy.databinding.FragmentStoreBinding
import com.drohealth.pharmacy.views.adapter.BagClickListener
import com.drohealth.pharmacy.views.adapter.BagRecyclerAdapter
import com.drohealth.pharmacy.views.adapter.ProductClickListener
import com.drohealth.pharmacy.views.adapter.ProductRecyclerAdapter
import com.drohealth.pharmacy.views.ui.store.StoreFragmentDirections

class BagFragment : Fragment() {

    private lateinit var binding: FragmentBagBinding


    private lateinit var bagAdapter : BagRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBagBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bagAdapter = BagRecyclerAdapter(requireContext(), BagClickListener {

        })

        binding.bagRv.adapter = bagAdapter

        getDummyList()
    }

    private fun getDummyList() {
        val list = arrayListOf<String>()
        for(i in 1..10){
            list.add("Item $i")
        }
        bagAdapter.submitList(list)
    }

}