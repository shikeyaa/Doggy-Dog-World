package com.example.doggydogworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.doggydogworld.application.ImageApplication
import com.example.doggydogworld.data.ImageEntity
//import com.example.doggydogworld.databinding.ActivityLastImageBinding

class MainActivity : AppCompatActivity() {

   // private lateinit var binding: MainActivity
    private val viewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as ImageApplication).database.imageDao())
    }
    private lateinit var imageBanner:ImageView
    private lateinit var image1:ImageView
    private lateinit var image2:ImageView
    private lateinit var image3:ImageView
    private lateinit var image4:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val previousButton: Button = findViewById(R.id.previous_dog_image_view)

        previousButton.setOnClickListener {
         val intent = Intent(this, LastImageActivity::class.java)
            startActivity(intent)
}
      //  (binding.previousButton.setOnClickListener {finish()})

       // val previous: Button = findViewById(R.id.previous_dog_image_view)

        imageBanner = findViewById(R.id.bannerImage)
        image1 = findViewById(R.id.dogBonePic1)
        image2 = findViewById(R.id.dogBonePic2)
        image3 = findViewById(R.id.dogBonePic3)
        image4 = findViewById(R.id.dogBonePic4)
        animateGlobe()

        //3. observer sees new image
        viewModel.apiResponse.observe(this, {
            //4. new image gets loaded
            findViewById<ImageView>(R.id.ivSrcImage).load(
                it.message?.toUri()?.buildUpon()?.scheme("https")?.build()
            )
        })

        findViewById<Button>(R.id.btnRandomImage).setOnClickListener {
            val currentImage = viewModel.apiResponse.value!!.imageUrl
            val previousImage = currentImage?.let { it1 -> ImageEntity(imageUrl = it1) }
            viewModel.getRandomDog()
            if(previousImage!= null) {
               viewModel.currentImage()
            }
           // viewModel.deleteLastImage()
           // viewModel.insertNewImage(imageEntity = ImageEntity(id = 0,imageUrl = String()))
        }
       // (binding.previousButton).setOnClickListener {
           // (binding.previousButton.setOnClickListener {finish()})
       // }
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
}

//    C:/Users/shike/AndroidStudioProjects/Doggy-Dog-World