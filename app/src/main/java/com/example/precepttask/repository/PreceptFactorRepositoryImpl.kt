package com.example.precepttask.repository

import com.example.precepttask.network.ApiService
import com.example.precepttask.model.PreceptFactor
import com.example.precepttask.db.PreceptFactorDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreceptFactorRepositoryImpl @Inject constructor(
    private val preceptFactorDao: PreceptFactorDao,
    private val apiService: ApiService
) : PreceptFactorRepository {

    override suspend fun refreshData() {
        try {
            val response = apiService.getPreceptFactors()
            withContext(Dispatchers.IO) {
                preceptFactorDao.deleteAll()
                preceptFactorDao.insertAll(response)
            }
        } catch (e: Exception) {
            println("Error refreshing ${e.message}")
            throw e
        }
    }

    override fun searchPreceptFactors(query: String): Flow<List<PreceptFactor>> =
        preceptFactorDao.search(query).flowOn(Dispatchers.IO)

    override fun getPreceptFactorsFromDb(): Flow<List<PreceptFactor>> =
        preceptFactorDao.getAll().flowOn(Dispatchers.IO)
}