package com.covid.covidapp.di

import com.covid.covidapp.BuildConfig
import com.covid.covidapp.datasources.CovidLocalDataSource
import com.covid.covidapp.datasources.CovidRemoteDataSource
import com.covid.covidapp.network.NetworkConnectionInterceptor
import com.covid.covidapp.network.QueryParamsInterceptor
import com.covid.covidapp.network.service.CovidRestService
import com.covid.covidapp.repositories.CovidRepository
import com.covid.covidapp.repositories.CovidRepository.Companion.getInstance
import com.google.gson.Gson
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class CovidDataModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        queryParamsInterceptor: QueryParamsInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(queryParamsInterceptor).addInterceptor(networkConnectionInterceptor)
            .addInterceptor(httpLoggingInterceptor).build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        } else {
            level = HttpLoggingInterceptor.Level.NONE
        }

    }

    @Provides
    fun providesCovidRepository(
        remoteDataSource: CovidRemoteDataSource,
        localDataSource: CovidLocalDataSource
    ): CovidRepository = CovidRepository.getInstance(remoteDataSource, localDataSource)


    @Provides
    fun provideCovidRestService(
        client: Lazy<OkHttpClient>,
        gson: Gson
    ): CovidRestService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callFactory(client.get())
            .build()
            .create(CovidRestService::class.java)

}