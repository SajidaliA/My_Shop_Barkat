package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.os.Bundle
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityProductDetailBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.AddReviewFragment
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation
import com.peacetechsolution.myshopbarkat.util.setStatusBarColor

class ProductDetailActivity : BaseActivity() {
    private lateinit var mBinding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.colorPrimary)
        mBinding.arrowBack.setOnClickListener {
            finish()
        }
        mBinding.tvAddReview.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.container_product_detail, AddReviewFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }
    }
}