package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WantFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_want, container, false)
    }


}