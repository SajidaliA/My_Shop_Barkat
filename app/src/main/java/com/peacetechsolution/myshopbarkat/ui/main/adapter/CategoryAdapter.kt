package com.peacetechsolution.myshopbarkat.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.data.model.Product
import com.peacetechsolution.myshopbarkat.data.model.ProductCategory
import com.peacetechsolution.myshopbarkat.databinding.ItemCategoryBinding
import com.peacetechsolution.myshopbarkat.databinding.ItemProductBinding
import com.peacetechsolution.myshopbarkat.util.clickWithDebounce
import com.peacetechsolution.myshopbarkat.util.hide
import com.peacetechsolution.myshopbarkat.util.show

class CategoryAdapter(
    private var categories: ArrayList<ProductCategory>,
    private var clickInterface: ClickListener
) : RecyclerView.Adapter<CategoryAdapter.DataViewHolder>() {

    interface ClickListener {
        fun itemClick(position: Int)
        fun editClick(position: Int)
        fun deleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.binding.edit.setOnClickListener { clickInterface.editClick(position) }
        holder.binding.delete.setOnClickListener { clickInterface.deleteClick(position) }
    }

    class DataViewHolder(var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: ProductCategory) = binding.apply {
            mData = itemData
            executePendingBindings()
        }
    }

    fun setData(list: List<ProductCategory>) {
        categories.addAll(list)
        notifyDataSetChanged()
    }
}
