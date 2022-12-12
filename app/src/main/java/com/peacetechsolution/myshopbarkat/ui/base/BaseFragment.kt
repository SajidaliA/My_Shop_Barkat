package com.peacetechsolution.myshopbarkat.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.peacetechsolution.myshopbarkat.util.PreferenceProvider
import com.peacetechsolution.myshopbarkat.util.hideKeyboard

/**
 * Created by Sajid.
 */
abstract class BaseFragment : Fragment() {

    var mBaseActivity: BaseActivity? = null
    val mPreferenceProvider: PreferenceProvider?
        get() = mBaseActivity?.mPreferenceProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mBaseActivity = context
        }
    }

    override fun onDestroyView() {
        activity?.hideKeyboard()
        super.onDestroyView()
    }
}
