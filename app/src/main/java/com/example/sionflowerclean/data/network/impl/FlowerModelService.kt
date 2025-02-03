package com.example.sionflowerclean.data.network.impl

import com.example.sionflowerclean.core.RetrofitHelper
import com.example.sionflowerclean.data.model.PlantIdentificationResult
import com.example.sionflowerclean.data.network.interfaces.FlowerModelApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class FlowerModelService {
    private val retrofit = RetrofitHelper.getRetrofit("http://localhost:7158/")

    suspend fun recognizeFlower(imagePart: MultipartBody.Part): PlantIdentificationResult? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FlowerModelApiClient::class.java).Recognigtion(imagePart)
            if (response.isSuccessful) response.body() else null
        }
    }
}