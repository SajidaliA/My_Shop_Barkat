package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peacetechsolution.myshopbarkat.databinding.BottomSheetPostSelectionBinding

/**
 * Created by Sajid.
 */
class BottomSheetPostSelection(var clickListener: OnTypeClickListener) :
    BottomSheetDialogFragment() {
    private var buttonClicked = false
    private lateinit var mBinding: BottomSheetPostSelectionBinding

    enum class TYPE {
        Product, Offer, Bill
    }

    companion object {
        @JvmStatic
        fun newInstance(
            clickListener: OnTypeClickListener
        ): BottomSheetPostSelection = BottomSheetPostSelection(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetPostSelectionBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }



    // initialize code here
    private fun initUI() {
        mBinding.clProduct.setOnClickListener {
            buttonClicked = true
            this@BottomSheetPostSelection.dismiss()
            clickListener.onClick(TYPE.Product)
        }
        mBinding.clOffer.setOnClickListener {
            buttonClicked = true
            this@BottomSheetPostSelection.dismiss()
            clickListener.onClick(TYPE.Offer)
        }
        mBinding.clBill.setOnClickListener {
            buttonClicked = true
            this@BottomSheetPostSelection.dismiss()
            clickListener.onClick(TYPE.Bill)
        }
    }

    interface OnTypeClickListener {
        fun onClick(selectedType: TYPE)
    }
}
