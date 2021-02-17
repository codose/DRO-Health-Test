package com.drohealth.pharmacy.views.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.DialogAddBagDialogBinding

class SuccessDialogFragment : DialogFragment() {

    private lateinit var binding : DialogAddBagDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheet)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogAddBagDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemName = requireArguments().getString("item_name")
        binding.item.text = "$itemName has been added to your bag"
        binding.viewBag.setOnClickListener {
            findNavController().navigate(R.id.bagFragment)
            dismiss()
        }

        binding.done.setOnClickListener {
            dismiss()
        }

    }

    companion object {
        fun newInstance(itemName : String): SuccessDialogFragment =
                SuccessDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString("item_name", itemName)
                    }
                }
    }
}