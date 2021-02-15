package com.drohealth.pharmacy.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drohealth.pharmacy.databinding.ItemBagItemBinding
import com.drohealth.pharmacy.utils.hide
import com.drohealth.pharmacy.utils.show

class BagRecyclerAdapter(val context: Context, val clickListener: BagClickListener) : ListAdapter<String, BagRecyclerAdapter.BagViewHolder>(
    BagDiffUtilCallback()
) {
    inner class BagViewHolder(val binding : ItemBagItemBinding) : RecyclerView.ViewHolder(binding.root){
        private var count = 1
        fun bind(
            item: String,
            clickListener: BagClickListener,
            context: Context){
            binding.root.setOnClickListener {
                if(binding.expanded.isVisible){
                    binding.expanded.hide()
                }else{
                    binding.expanded.show()
                }
            }
            setCount()
            binding.bagAdd.setOnClickListener {
                if(count in 1..4){
                    count+=1
                    setCount()
                }
            }

            binding.bagMinus.setOnClickListener {
                if(count != 1){
                    count-=1
                    setCount()
                }
            }
        }

        private fun setCount(){
            binding.bagCount.text = count.toString()
            binding.productCount.text = "$count X"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        return from(parent)
    }

    private fun from(parent: ViewGroup) : BagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBagItemBinding.inflate(layoutInflater, parent,false)
        return BagViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener,context)
    }
}


class BagClickListener(val clickListener: (type : String) -> Unit){
    fun onClick(type : String) = clickListener(type)
}

class BagDiffUtilCallback : DiffUtil.ItemCallback<String>(){

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}