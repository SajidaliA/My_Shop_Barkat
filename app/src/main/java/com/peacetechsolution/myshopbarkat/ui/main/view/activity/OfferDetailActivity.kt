package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.os.Bundle
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityOfferDetailBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.util.setStatusBarColor

/**
 * Created by Sajid Ali Suthar
 */
class OfferDetailActivity: BaseActivity() {
    private lateinit var mBinding: ActivityOfferDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOfferDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.landingStatusBar)
        mBinding.arrowBack.setOnClickListener {
            finish()
        }
    }
}