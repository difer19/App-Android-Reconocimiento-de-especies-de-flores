package com.example.sionflowerclean.data.model

import com.google.gson.annotations.SerializedName

data class PlantIdentificationResult (
    var scientificName: String,
    var images: List<ImageInfo>
)