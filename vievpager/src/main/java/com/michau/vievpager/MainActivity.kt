package com.michau.vievpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagesList=listOf(
            R.drawable.alk1,
            R.drawable.alk2,
            R.drawable.alk3,
            R.drawable.alk4,
            R.drawable.alk5,
            R.drawable.alk6,
            R.drawable.alk7,
            R.drawable.alk8
        )

        val adapter=ViewPagerAdapter(imagesList)
        viewPager.adapter=adapter

    }
}
