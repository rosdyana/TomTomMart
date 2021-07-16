package com.rosdyana.tomtommart.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rosdyana.tomtommart.R
import com.rosdyana.tomtommart.ui.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopFragment()
        initBottomNavigation()
    }

    private fun shopFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ShopFragment()).commit()
    }

//    private fun cartFragment() {
//        supportFragmentManager.beginTransaction().replace(R.id.frame_container, CartFragment()).commit()
//    }

//    private fun searchFragment() {
//        supportFragmentManager.beginTransaction().replace(R.id.frame_container, SearchFragment()).commit()
//    }

    private fun initBottomNavigation() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shop -> shopFragment()
                //R.id.search -> searchFragment()
                //R.id.cart -> cartFragment()
            }
            true
        }
    }
}