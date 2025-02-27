package com.example.sionflowerclean.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sionflowerclean.data.model.ImageInfo

@Dao
interface ImageInfoDAO {
    @Insert
    fun insertNewImage(imageInfo: ImageInfo)
    @Delete
    fun deleteImage(imageInfo: ImageInfo)

    @Query("SELECT * FROM ImageInfoPlant")
    fun getAllImages(): List<ImageInfo>

    @Query("SELECT * FROM ImageInfoPlant WHERE idPlant = :idPlant")
    fun getImage(idPlant: Long): ImageInfo


}