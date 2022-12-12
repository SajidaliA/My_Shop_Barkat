package com.peacetechsolution.myshopbarkat.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.databinding.ActivityHomeBinding
import com.peacetechsolution.myshopbarkat.ui.base.BaseActivity
import com.peacetechsolution.myshopbarkat.ui.main.view.fragment.*
import com.peacetechsolution.myshopbarkat.util.addReplaceFragment

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity(){


    private lateinit var mBinding: ActivityHomeBinding
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyShopBarkat)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.elevation = 0F
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                mBinding.drawerLayout,
                R.string.nav_open,
                R.string.nav_close
            )
        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        openHomeFragment()

        mBinding.bottomNavigationView.menu.removeItem(R.id.bottom_nav_chat)
        mBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_home -> {
                    openHomeFragment()
                    supportActionBar?.title = getString(R.string.home)
                }
                R.id.bottom_nav_offers -> {
                    supportActionBar?.title = getString(R.string.offers)
                    notImplemented()
                    addReplaceFragment(R.id.home_container, OfferFragment(), false,
                        addToBackStack = false
                    )
                }
                R.id.bottom_nav_chat -> {
                    notImplemented()

                }
                R.id.bottom_nav_customer -> {
                    supportActionBar?.title = getString(R.string.customer)
                    addReplaceFragment(R.id.home_container, CustomerFragment(), false,
                        addToBackStack = false
                    )
                }
                else -> {
                    openHomeFragment()
                }
            }
            return@setOnItemSelectedListener true
        }
        mBinding.navView.setNavigationItemSelectedListener {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            when(it.itemId){
                R.id.nav_account -> {
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun openHomeFragment(){
        addReplaceFragment(R.id.home_container, HomeFragment(), false,
            addToBackStack = false
        )
    }

    private fun notImplemented() {
        Toast.makeText(this, getString(R.string.feature_not_implemented), Toast.LENGTH_SHORT).show()
    }
}
