package com.peacetechsolution.myshopbarkat.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.ProductFragment

/**
 * Created by Sajid Ali.
 */
class CategoryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return ProductFragment()
    }
}