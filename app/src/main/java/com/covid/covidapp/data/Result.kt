package com.covid.covidapp.data

sealed class Result<out T : Any, in Y : Any> {

    data class Success<out T : Any, in Y : Any>(val data: T) : Result<T, Y>()
    data class Error<out T : Any, in Y : Any>(
        val exception: Exception,
        var data: Any? = null
    ) :
        Result<T, Y>()

    override fun toString(): String {
        return when (this) {
            is Success<*, *> -> "Success[data=$data]"
            is Error<*, *> -> "Error[data=$data,exception=$exception]"
        }
    }

}