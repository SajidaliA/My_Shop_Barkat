package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityLandingBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.LoginFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.SignUpFragment
import com.peacetechsolution.myshopbarkat.util.Constant
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation
import com.peacetechsolution.myshopbarkat.util.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : BaseActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(R.color.landingStatusBar)
        if (mPreferenceProvider.getBooleanValue(Constant.IS_USER_LOGIN, false)) {
            startActivity(Intent(this@LandingActivity, HomeActivity::class.java))
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.lending_container, SignUpFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }

        binding.btnLogin.setOnClickListener {
            addReplaceFragmentWithAnimation(
                R.id.lending_container, LoginFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }
    }
}