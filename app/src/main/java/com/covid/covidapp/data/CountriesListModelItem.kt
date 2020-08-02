package com.covid.covidapp.data

data class CountriesListModelItem(
    val alpha2code: String,
    val alpha3code: String,
    val latitude: Double,
    val longitude: Double,
    val name: String
)