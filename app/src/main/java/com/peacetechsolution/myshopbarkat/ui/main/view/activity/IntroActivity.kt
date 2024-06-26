package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent = Intent(
            this,
            LandingActivity::class.java
        ).apply {
            intent.extras?.let { putExtras(it) }
        }
        startActivity(intent)
        finish()
    }
}
