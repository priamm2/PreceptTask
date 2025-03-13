package com.example.precepttask.network

import com.example.precepttask.model.PreceptFactor

interface ApiService {
    suspend fun getPreceptFactors(): List<PreceptFactor>
}