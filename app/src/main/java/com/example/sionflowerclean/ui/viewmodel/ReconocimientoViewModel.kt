package com.example.sionflowerclean.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sionflowerclean.data.model.PlantIdentificationResult
import com.example.sionflowerclean.domain.ReconocimientoUseCase
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ReconocimientoViewModel : ViewModel(){
    val plantIdentificationResult = MutableLiveData<PlantIdentificationResult?>()
    val informationState = MutableLiveData<Boolean>()
    val progressBarState = MutableLiveData<Boolean>()

    private val reconocimientoUseCase = ReconocimientoUseCase()

    fun recognizeFlower(imagePart: MultipartBody.Part){
        viewModelScope.launch {
            val result = reconocimientoUseCase(imagePart)
            changeStateProgressBar(false)
            plantIdentificationResult.postValue(result)
            informationState.postValue(true)
        }
    }

    fun closePopUp(){
        informationState.postValue(false)
    }

    fun changeStateProgressBar(state: Boolean){
        progressBarState.postValue(state)
    }





}