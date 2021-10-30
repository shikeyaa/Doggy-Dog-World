package com.example.doggydogworld.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private  val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface  DogApiService{

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Dog
    //
    @GET("breeds/{breed}/image/random")
    suspend fun getRandomBreedImage(@Path ("breed") breed: String): Dog
}

object DogApi {
    val retrofitService: DogApiService by lazy { retrofit.create(DogApiService::class.java)}
}