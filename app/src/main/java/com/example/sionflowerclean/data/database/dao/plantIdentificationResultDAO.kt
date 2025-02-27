package com.example.sionflowerclean.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sionflowerclean.data.model.PlantIdentificationResult

@Dao
interface plantIdentificationResultDAO {
    @Insert
    fun insertNewPlant(plantIdentificationResult: PlantIdentificationResult)

    @Delete
    fun deletePlant(plantIdentificationResult: PlantIdentificationResult)

    @Query("SELECT * FROM Plant")
    fun getAllPlants(): List<PlantIdentificationResult>

    @Query("SELECT * FROM Plant WHERE scientificName = :scientificName")
    fun getPlant(scientificName: String): PlantIdentificationResult
}