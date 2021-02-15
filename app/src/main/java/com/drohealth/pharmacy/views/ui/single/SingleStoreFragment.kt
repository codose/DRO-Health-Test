package com.drohealth.pharmacy.views.ui.single

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drohealth.pharmacy.databinding.FragmentSingleStoreBinding
import com.drohealth.pharmacy.views.ui.dialog.SuccessDialogFragment


class SingleStoreFragment : Fragment() {

    private lateinit var binding : FragmentSingleStoreBinding
    private var count = 1 //Initial Count of Product
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addToBagBtn.setOnClickListener {
            openSuccessDialog()
        }

        initView()

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
    }

    private fun setCount(){
        binding.bagAddCount.text = count.toString()
    }

    private fun openSuccessDialog() {
        val bottomSheetDialogFragment = SuccessDialogFragment.newInstance("Item Name")
        bottomSheetDialogFragment.isCancelable = true
        bottomSheetDialogFragment.setTargetFragment(this, 700)
        bottomSheetDialogFragment.show(requireFragmentManager().beginTransaction(),"BottomSheetDialog")
    }

}