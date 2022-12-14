package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.BottomSheetFilterBinding
import com.peacetechsolution.myshopbarkat.util.Constant.LESS_THEN_100
import com.peacetechsolution.myshopbarkat.util.Constant._1000_OR_MORE
import com.peacetechsolution.myshopbarkat.util.Constant._100_TO_250
import com.peacetechsolution.myshopbarkat.util.Constant._250_TO_500
import com.peacetechsolution.myshopbarkat.util.Constant._500_TO_1000

/**
 * Created by Sajid.
 */
class BottomSheetFilter(var clickListener: OnFilterApplyClickListener) :
    BottomSheetDialogFragment() {
    private lateinit var mBinding: BottomSheetFilterBinding
    private var selectedPriceFilter = ""

    companion object {
        @JvmStatic
        fun newInstance(
            clickListener: OnFilterApplyClickListener
        ): BottomSheetFilter = BottomSheetFilter(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetFilterBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    // initialize code here
    private fun initUI() {
        mBinding.apply {
            tvApplyFilter.setOnClickListener {
                this@BottomSheetFilter.dismiss()
                clickListener.onFilterApply(selectedPriceFilter)
            }
            tvLessThen100.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tvLessThen100.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tvLessThen100.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedPriceFilter = LESS_THEN_100
            }
            tv100to250.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tv100to250.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv100to250.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedPriceFilter = _100_TO_250
            }
            tv250to500.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tv250to500.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv250to500.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedPriceFilter = _250_TO_500
            }
            tv500to1000.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tv500to1000.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv500to1000.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedPriceFilter = _500_TO_1000
            }
            tv1000orMore.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tv1000orMore.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv1000orMore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedPriceFilter = _1000_OR_MORE
            }
        }

    }

    private fun setDefaultView() {
        mBinding.apply {
            context?.let { context ->
                tvLessThen100.setTextColor(ContextCompat.getColor(context, R.color.black))
                tv100to250.setTextColor(ContextCompat.getColor(context, R.color.black))
                tv250to500.setTextColor(ContextCompat.getColor(context, R.color.black))
                tv500to1000.setTextColor(ContextCompat.getColor(context, R.color.black))
                tv1000orMore.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvLessThen100.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tv100to250.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tv250to500.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tv500to1000.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tv1000orMore.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tvApplyFilter.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                tvApplyFilter.isEnabled = true
            }
        }
    }

    interface OnFilterApplyClickListener {
        fun onFilterApply(selectedPriceFilter: String)
    }
}
