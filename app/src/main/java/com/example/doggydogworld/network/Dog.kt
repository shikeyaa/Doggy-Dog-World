package com.example.doggydogworld.network

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi

data class Dog(

    @Json(name = "message")
    val message: String,

    @Json(name = "status")
    val status: String,
    val imageUrl: String,
)

/*fun main() {
    val adapter= Moshi.Builder().build().adapter<Dog>(Dog::class.java)
    adapter.fromJson("{}")
}*/