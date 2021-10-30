package com.example.doggydogworld.network

import com.squareup.moshi.Json

data class Dog(
    @Json(name = "message")
    val message:String,

    @Json(name = "status")
    val status:String
)