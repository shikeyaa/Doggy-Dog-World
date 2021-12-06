package com.example.doggydogworld.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert
    suspend fun insertImage(dogImageEntity: ImageEntity)
    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<ImageEntity>>
    @Query("SELECT * FROM images ORDER BY id DESC LIMIT 1")
    fun getNewImage() : ImageEntity
    @Query("SELECT * FROM images ORDER BY id DESC LIMIT 1")
    fun deleteImage() : ImageEntity

}