package com.covid.covidapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun providesApplication(): Application = application

    @Provides
    fun providesApplicationContext(): Context = application

}