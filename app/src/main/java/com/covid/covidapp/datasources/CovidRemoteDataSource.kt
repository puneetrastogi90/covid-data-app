package com.covid.covidapp.datasources


import com.covid.covidapp.network.service.CovidRestService
import javax.inject.Inject

class CovidRemoteDataSource @Inject constructor(val service: CovidRestService) {


}