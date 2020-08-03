package com.covid.covidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_list")
data class CountriesListModelItem(
    val alpha2code: String?,
    val alpha3code: String?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}