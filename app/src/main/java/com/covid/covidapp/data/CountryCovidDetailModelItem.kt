package com.covid.covidapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "countries_detail",
    indices = arrayOf(Index(value = arrayOf("code"), unique = true))
)
data class CountryCovidDetailModelItem(
    val code: String,
    val confirmed: Int,
    val country: String,
    val critical: Int,
    val deaths: Int,
    val lastChange: String?,
    val lastUpdate: String?,
    val latitude: Double,
    val longitude: Double,
    val recovered: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}