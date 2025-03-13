package com.example.precepttask.network

import com.example.precepttask.model.PreceptFactor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiServiceImpl @Inject constructor(private val client: HttpClient) : ApiService {
    private val BASE_URL = "http://81.161.220.59:8709/api/"

    override suspend fun getPreceptFactors(): List<PreceptFactor> {
        return client.get(BASE_URL + "precept-factor").body()
    }
}