package com.covid.covidapp.repositories


import com.covid.covidapp.datasources.CovidLocalDataSource
import com.covid.covidapp.datasources.CovidRemoteDataSource
import javax.inject.Inject

class CovidRepository @Inject constructor(
    private val covidRemoteDataSource: CovidRemoteDataSource,
    private val covidLocalDataSource: CovidLocalDataSource
) {


    companion object {
        @Volatile
        private var INSTANCE: CovidRepository? = null

        fun getInstance(
            covidRemoteDataSource: CovidRemoteDataSource,
            covidLocalDataSource: CovidLocalDataSource
        ): CovidRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: CovidRepository(
                    covidRemoteDataSource, covidLocalDataSource
                ).also { INSTANCE = it }
            }
        }

    }
}