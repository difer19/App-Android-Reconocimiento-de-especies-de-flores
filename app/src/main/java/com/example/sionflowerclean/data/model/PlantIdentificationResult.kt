package com.example.sionflowerclean.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Plant")
data class PlantIdentificationResult (
    @PrimaryKey var idFlower : Long,
    var scientificName: String,
    var genus: String,
    var family: String,
    var scientificNameFull: String,
    var commonNames: List<String>,
    @Ignore
    var images: List<ImageInfo>
)