package com.rosdyana.tomtommart.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.ui.cart.CartFragment
import com.rosdyana.tomtommart.ui.shop.ShopFragment
import com.rosdyana.tomtommart.utils.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val openCartFragment by lazy {
        intent.getBooleanExtra(Constant.CART, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (openCartFragment) {
            true -> cartFragment()
            false -> shopFragment()
        }
        initBottomNavigation()
    }

    private fun shopFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ShopFragment())
            .commit()
    }

    private fun cartFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, CartFragment())
            .commit()
    }

    private fun initBottomNavigation() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shop -> shopFragment()
                R.id.cart -> cartFragment()
            }
            true
        }
    }
}