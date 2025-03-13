package com.example.precepttask.repository

import com.example.precepttask.model.PreceptFactor
import kotlinx.coroutines.flow.Flow

interface PreceptFactorRepository {
    suspend fun refreshData()
    fun searchPreceptFactors(query: String): Flow<List<PreceptFactor>>
    fun getPreceptFactorsFromDb(): Flow<List<PreceptFactor>>
}