package com.example.doggydogworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var imageBanner:ImageView
    private lateinit var image1:ImageView
    private lateinit var image2:ImageView
    private lateinit var image3:ImageView
    private lateinit var image4:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageBanner = findViewById(R.id.bannerImage)
        image1 = findViewById(R.id.dogBonePic1)
        image2 = findViewById(R.id.dogBonePic2)
        image3 = findViewById(R.id.dogBonePic3)
        image4 = findViewById(R.id.dogBonePic4)
        animateGlobe()

      //  image2 = findViewById(R.id.dogBonePic1)
       // animateBone()


        //3. observer sees new image
        viewModel.apiResponse.observe(this, {
            //4. new image gets loaded
            findViewById<ImageView>(R.id.ivSrcImage).load(
                it.message.toUri().buildUpon().scheme("https").build()
            )
        })
        //1. click button
        findViewById<Button>(R.id.btnRandomImage).setOnClickListener {
            //2. new random image gets called
            viewModel.getRandomDog()
        }

    }

    private fun animateGlobe()  {
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        val shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1)
        val shake2 = AnimationUtils.loadAnimation(this, R.anim.shake2)
        val shake3 = AnimationUtils.loadAnimation(this, R.anim.shake3)
        val shake4 = AnimationUtils.loadAnimation(this, R.anim.shake4)



        imageBanner.animation = rotate
        image1.animation = shake1
        image2.animation = shake2
        image3.animation = shake3
        image4.animation = shake4




    }
   /* private  fun animateBone() {
        val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
        image2.animation = shake
    }*/

}