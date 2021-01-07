package com.example.furnitureshop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.furnitureshop.databinding.ActivityMainBinding
import com.example.furnitureshop.view.CheckoutFragment
import com.example.furnitureshop.view.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val checkoutFragment = CheckoutFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    setFragment(homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_cart -> {
                    setFragment(checkoutFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_fav -> {
                    Toast.makeText(this, "Segera Datang", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_location -> {
                    Toast.makeText(this, "Segera Datang", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    Toast.makeText(this, "Segera Datang", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
        setFragment(homeFragment)
    }

    private fun setFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }
}