package com.example.sionflowerclean.domain

import com.example.sionflowerclean.data.model.PlantIdentificationResult
import com.example.sionflowerclean.data.repository.ReconocimientoRepository
import okhttp3.MultipartBody

class ReconocimientoUseCase {
    private val repository = ReconocimientoRepository()
    suspend operator fun invoke(imagePart: MultipartBody.Part) : PlantIdentificationResult? =
                            repository.recognizeFlower(imagePart)
}