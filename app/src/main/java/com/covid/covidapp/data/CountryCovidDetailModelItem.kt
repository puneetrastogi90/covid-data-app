package com.covid.covidapp.data

data class CountryCovidDetailModelItem(
    val code: String,
    val confirmed: Int,
    val country: String,
    val critical: Int,
    val deaths: Int,
    val lastChange: String,
    val lastUpdate: String,
    val latitude: Double,
    val longitude: Double,
    val recovered: Int
)