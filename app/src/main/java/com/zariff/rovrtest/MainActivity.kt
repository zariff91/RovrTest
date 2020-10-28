package com.zariff.rovrtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zariff.rovrtest.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val home = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, home).commit()
    }

}
