package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityProductDetailBinding
import com.peacetechsolution.myshopbarkat.util.setStatusBarColor

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarColor(R.color.colorPrimary)
        mBinding.arrowBack.setOnClickListener {
            finish()
        }
    }
}