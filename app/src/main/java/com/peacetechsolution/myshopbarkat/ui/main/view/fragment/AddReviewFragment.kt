package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentAddReviewBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment

class AddReviewFragment : BaseFragment() {

    private lateinit var mBinding: FragmentAddReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAddReviewBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.arrowBack.setOnClickListener {
            activity?.onBackPressed()
        }
        mBinding.btnSubmitReview.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}