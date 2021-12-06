package com.example.doggydogworld

import androidx.lifecycle.*
import com.example.doggydogworld.data.ImageDao
import com.example.doggydogworld.data.ImageEntity
import com.example.doggydogworld.network.Dog
import com.example.doggydogworld.network.DogApi
import kotlinx.coroutines.launch

class MainViewModel(private val imageDao: ImageDao) : ViewModel() {

    private val _apiResponse = MutableLiveData<Dog>()
    val apiResponse: LiveData<Dog> = _apiResponse



    init {
        getRandomDog()
    }

    fun getRandomDog() {
        viewModelScope.launch {
            _apiResponse.value = DogApi.retrofitService.getRandomDogImage()
        }
    }



    fun insertNewImage(imageEntity: ImageEntity) {
        viewModelScope.launch {
            imageDao.insertImage(imageEntity)
        }
    }

    fun deleteLastImage() {
        viewModelScope.launch {
            imageDao.deleteImage()
        }
    }

    fun getAllImagesList(): LiveData<List<ImageEntity>> {
        return imageDao.getAllImages().asLiveData()
    }


   class MainViewModelFactory(val dogImageDao: ImageDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dogImageDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}