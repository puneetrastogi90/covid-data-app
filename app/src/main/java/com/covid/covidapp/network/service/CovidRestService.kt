package com.covid.covidapp.network.service

import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.CountryCovidDetailModel
import com.covid.covidapp.data.WorldReportModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidRestService {

    @GET("totals")
    suspend fun getWorldReportService(): Response<WorldReportModel>

    @GET("help/countries")
    suspend fun getCountriesListService(): Response<CountriesListModel>

    @GET("country/code")
    suspend fun getCountryCovidDetailService(@Query("code") countryCode: String): Response<CountryCovidDetailModel>
}