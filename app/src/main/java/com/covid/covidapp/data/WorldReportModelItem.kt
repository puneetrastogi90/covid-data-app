package com.covid.covidapp.data

data class WorldReportModelItem(
    val confirmed: Int,
    val critical: Int,
    val deaths: Int,
    val lastChange: String,
    val lastUpdate: String,
    val recovered: Int
)