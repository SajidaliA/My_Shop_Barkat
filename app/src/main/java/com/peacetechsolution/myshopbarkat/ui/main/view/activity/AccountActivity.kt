package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityAccountBinding
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.CategoryListFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.EditProfileFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.PurchasesFragment
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation

class AccountActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mBinding.tvEdit.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.container_account, EditProfileFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }
        mBinding.tvPurchases.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.container_account, PurchasesFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}