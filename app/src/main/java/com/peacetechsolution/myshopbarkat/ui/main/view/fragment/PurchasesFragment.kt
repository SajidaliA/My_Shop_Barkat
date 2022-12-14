package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentPurchasesBinding


class PurchasesFragment : Fragment() {

    private lateinit var mBinding: FragmentPurchasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentPurchasesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

}