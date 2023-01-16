package com.peacetechsolution.myshopbarkat.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.data.model.Offer
import com.peacetechsolution.myshopbarkat.data.model.Product
import com.peacetechsolution.myshopbarkat.databinding.ItemOfferBinding
import com.peacetechsolution.myshopbarkat.databinding.ItemProductBinding
import com.peacetechsolution.myshopbarkat.util.hide
import com.peacetechsolution.myshopbarkat.util.show

class OfferAdapter(
    private var offerList: ArrayList<Offer>,
    private var clickInterface: ClickListener
) : RecyclerView.Adapter<OfferAdapter.DataViewHolder>() {

    interface ClickListener {
        fun itemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_offer,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = offerList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(offerList[position])
        holder.binding.clMain.setOnClickListener {
            clickInterface.itemClick(position)
        }
    }

    class DataViewHolder(var binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: Offer) = binding.apply {
            mData = itemData
            executePendingBindings()
        }
    }

    fun setData(list: List<Offer>, loadMore: Boolean) {
        val oldListSize = this.offerList.size
        if (!loadMore) {
            offerList.clear()
            offerList.addAll(list)
        } else {
            offerList.addAll(list)
        }
        notifyItemRangeChanged(oldListSize, list.size)
    }
}
