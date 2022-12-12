package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.BottomSheetNeedPermissionBinding

/**
 * Created by Sajid.
 */
class BottomSheetNeedPermission(var selectedType: TYPE, var clickListener: OnClickListener) :
    BottomSheetDialogFragment() {
    private var buttonClicked = false
    private lateinit var mBinding: BottomSheetNeedPermissionBinding

    enum class TYPE {
        PERMISSION, GPS, AFTER_DENY_ONCE, AFTER_ALWAYS_DENY
    }

    companion object {
        @JvmStatic
        fun newInstance(
            selectedType: TYPE,
            clickListener: OnClickListener
        ): BottomSheetNeedPermission = BottomSheetNeedPermission(selectedType, clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetNeedPermissionBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun setTextFromType() {
        mBinding.apply {
            when (selectedType) {
                TYPE.PERMISSION -> {
                    tvTitle.setText(R.string.label_we_need_your_permission)
                    tvMessage.setText(R.string.we_need_your_location_permission)
                    btnGotIt.text = getString(R.string.allow)
                }
                TYPE.GPS -> {
                    tvTitle.setText(R.string.label_we_need_your_permission)
                    tvMessage.setText(R.string.enable_gps_text)
                    btnGotIt.text = getString(R.string.allow)
                }
                TYPE.AFTER_DENY_ONCE -> {
                    tvTitle.setText(R.string.permission_required)
                    tvMessage.setText(R.string.permission_required_details)
                    btnGotIt.text = getString(R.string.fetch_location)
                }
                TYPE.AFTER_ALWAYS_DENY -> {
                    tvTitle.setText(R.string.label_we_need_your_permission)
                    tvMessage.setText(R.string.label_go_to_settings)
                    btnGotIt.text = getString(R.string.take_me_to_settings)
                }
            }
        }
    }

    // initialize code here
    private fun initUI() {
        setTextFromType()
        mBinding.btnGotIt.setOnClickListener {
            buttonClicked = true
            this@BottomSheetNeedPermission.dismiss()
            clickListener.onClick(selectedType)
        }
    }

    interface OnClickListener {
        fun onClick(selectedType: TYPE)
        fun onDismissBS()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        if (!buttonClicked) {
            clickListener.onDismissBS()
        }
    }
}
