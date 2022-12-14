package com.peacetechsolution.myshopbarkat.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.data.model.Product
import com.peacetechsolution.myshopbarkat.databinding.ItemProductBinding
import com.peacetechsolution.myshopbarkat.util.clickWithDebounce
import com.peacetechsolution.myshopbarkat.util.hide
import com.peacetechsolution.myshopbarkat.util.show

class ProductAdapter(
    private var userList: ArrayList<Product>,
    private var clickInterface: ClickListener
) : RecyclerView.Adapter<ProductAdapter.DataViewHolder>() {

    interface ClickListener {
        fun itemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.binding.cardMain.setOnClickListener {
            clickInterface.itemClick(position)
        }
        if (position != 0 && position %3 == 0){
            holder.binding.tvOutOfStock.show()
            holder.binding.viewOutOfStock.show()
        } else{
            holder.binding.tvOutOfStock.hide()
            holder.binding.viewOutOfStock.hide()
        }

        if (position != 0 && position %2 == 0){
            holder.binding.tvOffer.show()
        } else{
            holder.binding.tvOffer.hide()
        }
    }

    class DataViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: Product) = binding.apply {
            mData = itemData
            executePendingBindings()
        }
    }

    fun setData(list: List<Product>, loadMore: Boolean) {
        val oldListSize = this.userList.size
        if (!loadMore) {
            userList.clear()
            userList.addAll(list)
        } else {
            userList.addAll(list)
        }
        notifyItemRangeChanged(oldListSize, list.size)
    }
}
