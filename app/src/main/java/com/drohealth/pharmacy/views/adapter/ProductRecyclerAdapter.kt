package com.drohealth.pharmacy.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.ItemProductItemBinding
import com.drohealth.pharmacy.model.Product

class ProductRecyclerAdapter(val context: Context, val clickListener: ProductClickListener) : ListAdapter<Product, ProductRecyclerAdapter.ProductViewHolder>(
    ProductDiffUtilCallback()
) {
    inner class ProductViewHolder(val binding : ItemProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: Product,
            clickListener: ProductClickListener,
            context: Context){
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.productName.text = item.name
            binding.productPrice.text = "${context.getString(R.string.naira)}${item.price}"
            binding.productType.text = item.type
            Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.productImage)
            binding.productDose.text = item.dispensedIn
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


class ProductClickListener(val clickListener: (type : Product) -> Unit){
    fun onClick(type : Product) = clickListener(type)
}

class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>(){

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}