package com.example.doggydogworld.application

import android.app.Application
import com.example.doggydogworld.data.ImageDatabase

class ImageApplication : Application() {
    val database: ImageDatabase by lazy {
        ImageDatabase.getDatabase(this)
    }
}