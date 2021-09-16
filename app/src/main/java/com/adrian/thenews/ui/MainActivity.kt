package com.adrian.thenews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.adrian.thenews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val favoriteFragment = FavoriteFragment()
    private val homeFragment = HomeFragment()
    private val fragmentManager = supportFragmentManager
    private var fragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        fragmentManager.beginTransaction().add(R.id.frame_layout, favoriteFragment)
            .hide(favoriteFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, homeFragment).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    fragmentManager.beginTransaction().hide(fragment).show(homeFragment).commit()
                    fragment = homeFragment
                }
                R.id.favorite -> {
                    fragmentManager.beginTransaction().hide(fragment).show(favoriteFragment)
                        .commit()
                    fragment = favoriteFragment
                }
            }
            true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}