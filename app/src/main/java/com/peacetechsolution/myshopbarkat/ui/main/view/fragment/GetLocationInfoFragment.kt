package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentGetLocationInfoBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.util.Constant
import com.peacetechsolution.myshopbarkat.util.addReplaceFragmentWithAnimation


class GetLocationInfoFragment : BaseFragment() {

    private lateinit var mBinding: FragmentGetLocationInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentGetLocationInfoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvUserName.text = getString(
            R.string.hi_user, mPreferenceProvider?.getValue(
            Constant.USER_NAME_KEY, ""))
        mBinding.btnOkLetsGo.setOnClickListener {
            activity?.addReplaceFragmentWithAnimation(
                R.id.lending_container, OnBoardLocationFragment(),
                addFragment = true,
                addToBackStack = true,
                R.anim.slide_in, R.anim.slide_out
            )
        }
    }
}