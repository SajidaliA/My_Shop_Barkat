package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.BottomSheetShowHistoryBinding
import com.peacetechsolution.myshopbarkat.util.Constant.LAST_1_YEAR
import com.peacetechsolution.myshopbarkat.util.Constant.LAST_30_DAYS
import com.peacetechsolution.myshopbarkat.util.Constant.LAST_6_MONTHS
import com.peacetechsolution.myshopbarkat.util.Constant.LESS_THEN_100
import com.peacetechsolution.myshopbarkat.util.Constant.SHOW_ALL
import com.peacetechsolution.myshopbarkat.util.Constant._1000_OR_MORE
import com.peacetechsolution.myshopbarkat.util.Constant._100_TO_250
import com.peacetechsolution.myshopbarkat.util.Constant._250_TO_500
import com.peacetechsolution.myshopbarkat.util.Constant._500_TO_1000

/**
 * Created by Sajid.
 */
class BottomSheetShowHistory(var clickListener: OnHistoryApplyClickListener) :
    BottomSheetDialogFragment() {
    private lateinit var mBinding: BottomSheetShowHistoryBinding
    private var selectedHistory = ""

    companion object {
        @JvmStatic
        fun newInstance(
            clickListener: OnHistoryApplyClickListener
        ): BottomSheetShowHistory = BottomSheetShowHistory(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetShowHistoryBinding.inflate(inflater, container, false)
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
                this@BottomSheetShowHistory.dismiss()
                clickListener.onHistoryApply(selectedHistory)
            }
            tvLast30Days.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tvLast30Days.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tvLast30Days.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedHistory = LAST_30_DAYS
            }
            tvLast6Months.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tvLast6Months.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tvLast6Months.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedHistory = LAST_6_MONTHS
            }
            tvLast1Year.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tvLast1Year.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tvLast1Year.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedHistory = LAST_1_YEAR
            }
            tvShowAll.setOnClickListener {
                setDefaultView()
                context?.let { context ->
                    tvShowAll.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tvShowAll.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                }
                selectedHistory = SHOW_ALL
            }
        }

    }

    private fun setDefaultView() {
        mBinding.apply {
            context?.let { context ->
                tvLast30Days.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvLast6Months.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvLast1Year.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvShowAll.setTextColor(ContextCompat.getColor(context, R.color.black))
                tvLast30Days.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tvLast6Months.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tvLast1Year.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tvShowAll.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                tvApplyFilter.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                tvApplyFilter.isEnabled = true
            }
        }
    }

    interface OnHistoryApplyClickListener {
        fun onHistoryApply(selectedHistory: String)
    }
}

