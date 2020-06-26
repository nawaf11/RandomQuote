package com.nawaf.example.randomquote.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nawaf.example.randomquote.R
import com.nawaf.example.randomquote.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}