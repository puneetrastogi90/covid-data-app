package com.covid.covidapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.covid.covidapp.daos.WorldDataDao
import com.covid.covidapp.data.WorldReportModelItem

@Database(entities = [WorldReportModelItem::class], version = 1)
abstract class CovidRoomDatabase : RoomDatabase() {

    abstract fun getWorldDataDao(): WorldDataDao
}