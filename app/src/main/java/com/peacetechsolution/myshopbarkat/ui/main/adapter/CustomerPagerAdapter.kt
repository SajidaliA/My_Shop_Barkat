package com.peacetechsolution.myshopbarkat.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.NormalCustomerFragment
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.PremiumCustomerFragment

/**
 * Created by Sajid Ali.
 */
class CustomerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                NormalCustomerFragment()
            }
            1 -> {
                PremiumCustomerFragment()
            }
            else -> {
                NormalCustomerFragment()
            }
        }
    }
}