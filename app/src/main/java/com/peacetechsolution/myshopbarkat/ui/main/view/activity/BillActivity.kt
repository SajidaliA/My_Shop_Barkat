package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.peacetechsolution.myshopbarkat.databinding.ActivityBillBinding

class BillActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityBillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return true
    }
}