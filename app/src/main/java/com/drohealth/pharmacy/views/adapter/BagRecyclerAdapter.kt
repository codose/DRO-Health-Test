package com.drohealth.pharmacy.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drohealth.pharmacy.R
import com.drohealth.pharmacy.databinding.ItemBagItemBinding
import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.utils.CartOp
import com.drohealth.pharmacy.utils.hide
import com.drohealth.pharmacy.utils.show
import timber.log.Timber

class BagRecyclerAdapter(val context: Context, val clickListener: BagClickListener) : ListAdapter<Product, BagRecyclerAdapter.BagViewHolder>(
    BagDiffUtilCallback()
) {
    inner class BagViewHolder(val binding : ItemBagItemBinding) : RecyclerView.ViewHolder(binding.root){
        private var count = 1
        fun bind(
                item: Product,
                clickListener: BagClickListener,
                context: Context){
            binding.root.setOnClickListener {
                if(binding.expanded.isVisible){
                    binding.expanded.hide()
                }else{
                    binding.expanded.show()
                }
            }

            Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.productImage)

            count = item.count
            setCount(item)
            binding.bagAdd.setOnClickListener {
                if(count in 1..4){
                    count+=1
                    item.count = count
                    setCount(item)
                }
            }

            binding.productRemove.setOnClickListener {
                clickListener.onClick(item, CartOp.REMOVE)
            }

            binding.productName.text = item.name

            binding.productType.text = item.type

            binding.bagMinus.setOnClickListener {
                if(count != 1){
                    count-=1
                    item.count = count
                    setCount(item)
                }
            }
        }

        private fun setCount(item: Product){
            binding.bagCount.text = count.toString()
            binding.productPrice.text = "${context.getString(R.string.naira)}${count * item.price}"
            binding.productCount.text = "$count X"
            clickListener.onClick(item, CartOp.ALTER)
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


class BagClickListener(val clickListener: (type : Product, op : CartOp) -> Unit){
    fun onClick(type : Product, op : CartOp) = clickListener(type, op)
}

class BagDiffUtilCallback : DiffUtil.ItemCallback<Product>(){

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}