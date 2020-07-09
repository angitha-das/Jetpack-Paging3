package com.example.jetpack_paging3.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_paging3.R
import androidx.databinding.DataBindingUtil.setContentView
import com.example.jetpack_paging3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}