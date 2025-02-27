package com.example.sionflowerclean.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageInfoPlant")
data class ImageInfo (
    @PrimaryKey var idImage : Long,
    var organ: String,
    var author: String,
    var license: String,
    var url: String,
    var idPlant : Long
)