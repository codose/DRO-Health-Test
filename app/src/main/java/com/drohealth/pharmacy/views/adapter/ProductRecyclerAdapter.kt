package com.drohealth.pharmacy.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drohealth.pharmacy.databinding.ItemProductItemBinding

class ProductRecyclerAdapter(val context: Context, val clickListener: ProductClickListener) : ListAdapter<String, ProductRecyclerAdapter.ProductViewHolder>(
    ProductDiffUtilCallback()
) {
    inner class ProductViewHolder(val binding : ItemProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: String,
            clickListener: ProductClickListener,
            context: Context){
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return from(parent)
    }

    private fun from(parent: ViewGroup) : ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductItemBinding.inflate(layoutInflater, parent,false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener,context)
    }
}


class ProductClickListener(val clickListener: (type : String) -> Unit){
    fun onClick(type : String) = clickListener(type)
}

class ProductDiffUtilCallback : DiffUtil.ItemCallback<String>(){

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}