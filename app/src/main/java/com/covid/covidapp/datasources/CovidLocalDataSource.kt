package com.covid.covidapp.datasources


import com.covid.covidapp.daos.WorldDataDao
import com.covid.covidapp.data.WorldReportModelItem
import javax.inject.Inject

class CovidLocalDataSource @Inject constructor(val worldDataDao: WorldDataDao) {

    fun removeAll() {
        worldDataDao.nukeTable()
    }

    fun saveWorldreport(worldReportModelItem: WorldReportModelItem) {
        worldDataDao.insert(worldReportModelItem)
    }

    fun getCachedWorldReport(): WorldReportModelItem {
        return worldDataDao.getAllWorldReportFromDb().get(0)
    }
}