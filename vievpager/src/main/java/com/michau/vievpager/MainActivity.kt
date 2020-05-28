package com.michau.vievpager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagesList = listOf(
            R.drawable.alk1,
            R.drawable.alk2,
            R.drawable.alk3,
            R.drawable.alk4,
            R.drawable.alk5,
            R.drawable.alk6,
            R.drawable.alk7,
            R.drawable.alk8
        )

        val adapter = ViewPagerAdapter(imagesList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "tab ${tab?.text} reselected", Toast.LENGTH_SHORT ).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "tab ${tab?.text} unselected", Toast.LENGTH_SHORT ).show()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "tab ${tab?.text} selected", Toast.LENGTH_SHORT ).show()
            }
        })
    }
}
