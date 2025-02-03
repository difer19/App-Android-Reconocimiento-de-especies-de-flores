package com.example.sionflowerclean.data.network.interfaces


import com.example.sionflowerclean.data.model.PlantIdentificationResult
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FlowerModelApiClient {
    @Multipart
    @POST("api/FlowerRecognition")
    suspend fun Recognigtion(@Part image: MultipartBody.Part): Response<PlantIdentificationResult>
}