package com.covid.covidapp.di

import android.app.Application
import android.content.Context
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context
    fun appContext(): Application

}