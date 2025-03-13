package com.example.precepttask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "precept_factors")
data class PreceptFactor(
    @PrimaryKey
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("divisionType")
    val divisionType: String? = null,
    @SerialName("del")
    val del: String? = null
)