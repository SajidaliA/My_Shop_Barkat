package com.peacetechsolution.myshopbarkat.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.util.PreferenceProvider
import com.peacetechsolution.myshopbarkat.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Sajid.
 */

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var mPreferenceProvider: PreferenceProvider

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

    open fun notImplemented(context: Context) {
        Toast.makeText(context, getString(R.string.feature_not_implemented), Toast.LENGTH_SHORT).show()
    }
}
