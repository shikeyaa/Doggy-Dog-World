package com.example.doggydogworld

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import coil.load
import com.example.doggydogworld.application.ImageApplication
import com.example.doggydogworld.databinding.ActivityLastImageBinding

class LastImageActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLastImageBinding

    private val viewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as ImageApplication).database.imageDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLastImageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.getAllImagesList().observe(this,{ dogImage ->
            val prevImage = binding.previousDogImageView
            prevImage.load(dogImage[0].imageUrl)
            binding.currentButton.setOnClickListener { finish() }
        })

    }
}