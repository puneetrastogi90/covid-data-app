package com.covid.covidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "world_data")
data class WorldReportModelItem(
    var confirmed: Int,
    var critical: Int,
    var deaths: Int,
    var lastChange: String,
    var lastUpdate: String,
    var recovered: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}