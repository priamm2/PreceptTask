package com.example.precepttask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.precepttask.model.PreceptFactor
import kotlinx.coroutines.flow.Flow

@Dao
interface PreceptFactorDao {
    @Query("SELECT * FROM precept_factors")
    fun getAll(): Flow<List<PreceptFactor>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(preceptFactors: List<PreceptFactor>)

    @Query("DELETE FROM precept_factors")
    suspend fun deleteAll()

    @Query("SELECT * FROM precept_factors WHERE name LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<PreceptFactor>>
}