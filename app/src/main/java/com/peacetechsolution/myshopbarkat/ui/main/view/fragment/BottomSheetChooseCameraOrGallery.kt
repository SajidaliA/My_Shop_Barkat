package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peacetechsolution.myshopbarkat.databinding.BottomSheetChooseCameraOrGalleryBinding

/**
 * Created by Sajid.
 */
class BottomSheetChooseCameraOrGallery(var clickListener: OnChooseClickListener) :
    BottomSheetDialogFragment() {
    private var buttonClicked = false
    private lateinit var mBinding: BottomSheetChooseCameraOrGalleryBinding

    enum class TYPE {
        Camera, Gallery
    }

    companion object {
        @JvmStatic
        fun newInstance(
            clickListener: OnChooseClickListener
        ): BottomSheetChooseCameraOrGallery = BottomSheetChooseCameraOrGallery(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetChooseCameraOrGalleryBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }



    // initialize code here
    private fun initUI() {
        mBinding.clCamera.setOnClickListener {
            buttonClicked = true
            this@BottomSheetChooseCameraOrGallery.dismiss()
            clickListener.onClick(TYPE.Camera)
        }
        mBinding.clGallery.setOnClickListener {
            buttonClicked = true
            this@BottomSheetChooseCameraOrGallery.dismiss()
            clickListener.onClick(TYPE.Gallery)
        }
    }

    interface OnChooseClickListener {
        fun onClick(selectedType: TYPE)
    }
}
