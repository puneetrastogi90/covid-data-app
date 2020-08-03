package com.covid.covidapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.covid.covidapp.data.WorldReportModelItem

@Dao
interface WorldDataDao {

    @Insert
    fun insert(worldReportModelItem: WorldReportModelItem)

    @Query("DELETE FROM world_data")
    fun nukeTable()

    @Query("SELECT * FROM world_data")
    fun getAllWorldReportFromDb(): List<WorldReportModelItem>

}