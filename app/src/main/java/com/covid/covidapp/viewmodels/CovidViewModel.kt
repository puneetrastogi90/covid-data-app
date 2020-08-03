package com.covid.covidapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covid.covidapp.data.Result
import com.covid.covidapp.data.WorldReportModel
import com.covid.covidapp.exceptions.NoInternetException
import com.covid.covidapp.repositories.CovidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CovidViewModel @Inject constructor(private val repository: CovidRepository) : ViewModel() {
    private var job: Job? = null
    private val _worldCovidDataLiveData = MutableLiveData<WorldReportModel>()
    val worldCovidDataLiveData: LiveData<WorldReportModel>
        get() = _worldCovidDataLiveData

    private val _uiState = MutableLiveData<LoadingState>()
    val uiState: LiveData<LoadingState>
        get() = _uiState

    fun getWorldCovidData() {
        if (job?.isActive == true) {
            return
        }
        job = doGetWorldCovidData()
    }

    fun doGetWorldCovidData(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _uiState.value = LoadingState.Loading
            }
            val result = repository.getWorldData()
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    _uiState.value = LoadingState.Success("Success")
                    _worldCovidDataLiveData.value = result.data
                } else {
                    if (result is Result.Error && result.exception is NoInternetException) {
                        _uiState.value = LoadingState.Error(result.exception.message.toString())
                        _worldCovidDataLiveData.value = result.data as WorldReportModel
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