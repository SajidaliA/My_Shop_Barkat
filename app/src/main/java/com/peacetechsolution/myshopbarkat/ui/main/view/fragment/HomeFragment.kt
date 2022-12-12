package com.peacetechsolution.myshopbarkat.ui.main.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.tabs.TabLayoutMediator
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.FragmentHomeBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseFragment
import com.peacetechsolution.myshopbarkat.ui.main.adapter.CategoryPagerAdapter
import com.peacetechsolution.myshopbarkat.ui.main.view.activity.AddItemActivity
import com.peacetechsolution.myshopbarkat.util.Constant.SELECTION_TYPE
import com.peacetechsolution.myshopbarkat.util.hide
import com.peacetechsolution.myshopbarkat.util.show


class HomeFragment : BaseFragment(), BottomSheetPostSelection.OnTypeClickListener {

    private lateinit var mBinding: FragmentHomeBinding

    private lateinit var pagerAdapter : CategoryPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        openFreeItems()
        addCategories()
        setClickListener()
    }

    private fun addCategories() {
        mBinding.apply {
            pagerAdapter = CategoryPagerAdapter(this@HomeFragment)
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                val tabNames = listOf("Cover", "Charger", "Earphone", "Battery", "Data Cable", "Other")
                tab.text = tabNames[position]
            }.attach()

        }
    }

    private fun setClickListener() {
        mBinding.apply {
            context?.let {
                fab.setOnClickListener {
                    openSelectionBottomSheet()
                }
                ivClearSearch.setOnClickListener {
                    etSearch.setText("")
                }
                etSearch.doOnTextChanged { text, start, before, count ->
                    if (text.toString().isNotEmpty()) {
                        ivClearSearch.show()
                    } else {
                        ivClearSearch.hide()
                    }
                }
            }
        }
    }

    private fun openSelectionBottomSheet() {
        activity?.supportFragmentManager?.let { frm ->
            val bottomSheet =
                BottomSheetPostSelection.newInstance(this)
            bottomSheet.let {
                it.isCancelable = true
                it.show(frm, BottomSheetPostSelection::class.java.simpleName)
            }
        }
    }

    override fun onClick(selectedType: BottomSheetPostSelection.TYPE) {
        val intent = Intent(context, AddItemActivity::class.java)
        when (selectedType) {
            BottomSheetPostSelection.TYPE.Product -> {
                intent.putExtra(SELECTION_TYPE, BottomSheetPostSelection.TYPE.Product.name)
                startActivity(intent)
            }
            BottomSheetPostSelection.TYPE.Offer -> {
                intent.putExtra(SELECTION_TYPE, BottomSheetPostSelection.TYPE.Offer.name)
                notImplemented()
            }
        }

    }

    private fun notImplemented() {
        Toast.makeText(context, getString(R.string.feature_not_implemented), Toast.LENGTH_SHORT)
            .show()
    }


}