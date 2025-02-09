package com.example.sionflowerclean.data.repository

import com.example.sionflowerclean.data.model.PlantIdentificationResult
import com.example.sionflowerclean.data.network.impl.FlowerModelService
import okhttp3.MultipartBody

class ReconocimientoRepository {
    private val flowerModelService = FlowerModelService()

    suspend fun recognizeFlower(imagePart: MultipartBody.Part): PlantIdentificationResult? {
        val response = flowerModelService.recognizeFlower(imagePart)
        return response
    }
}