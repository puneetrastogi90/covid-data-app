package com.covid.covidapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covid.covidapp.data.CountriesListModel
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.exceptions.NoInternetException
import com.covid.covidapp.repositories.CovidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.covid.covidapp.data.Result

class CountriesListViewModel @Inject constructor(private val repository: CovidRepository) :
    ViewModel() {
    private var job: Job? = null
    private val _countriesListLiveData = MutableLiveData<CountriesListModel>()
    val countriesListLiveData: LiveData<CountriesListModel>
        get() = _countriesListLiveData

    private val _uiState = MutableLiveData<LoadingState>()
    val uiState: LiveData<LoadingState>
        get() = _uiState

    fun getCountriesList() {
        if (job?.isActive == true) {
            return
        }
        job = doGetCountriesList()
    }

    fun doGetCountriesList(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _uiState.value = LoadingState.Loading
            }
            val result = repository.getCountriesList()
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    _uiState.value = LoadingState.Success("Success")
                    _countriesListLiveData.value = result.data
                } else {
                    if (result is Result.Error && result.exception is NoInternetException) {
                        _uiState.value = LoadingState.Error(result.exception.message.toString())
                        _countriesListLiveData.value = result.data as CountriesListModel
                    }

                }
            }

        }
    }

    sealed class LoadingState {
        data class Error(val msg: String) : LoadingState()
        data class Success(val msg: String) : LoadingState()
        object Loading : LoadingState()
    }
}