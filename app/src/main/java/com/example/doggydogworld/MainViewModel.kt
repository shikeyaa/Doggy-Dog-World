package com.example.doggydogworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doggydogworld.network.Dog
import com.example.doggydogworld.network.DogApi
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private  val _apiResponse = MutableLiveData<Dog>()
    val apiResponse: LiveData<Dog> = _apiResponse
    //val breedType: String = ""


    init {
        getRandomDog()
    }

    fun getRandomDog() {
        viewModelScope.launch {
            _apiResponse.value = DogApi.retrofitService.getRandomDogImage()
        }
    }

    fun getRandomBreed(breed: String){
        viewModelScope.launch {
            _apiResponse.value = DogApi.retrofitService.getRandomBreedImage(breed)
        }
    }
}