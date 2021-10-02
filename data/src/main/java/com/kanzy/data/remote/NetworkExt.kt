package com.kanzy.data.remote

import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class DataState<out T> {
    data class Success<out T>(val response: T) : DataState<T>()
    data class Error(val error: Throwable) : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$response]"
            is Error -> "Error[exception=$error]"
        }
    }
}

val DataState<*>.isSuccess get() = this is DataState.Success

val DataState<*>.isError get() = this is DataState.Error

suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
    return try {
        val response = call()
        DataState.Success(response)
    } catch (ex: Throwable) {
        DataState.Error(ex)
    }
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): DataState<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                DataState.Success(body)
            else
                DataState.Error(Failure.EmptyResponse)
        } else {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                DataState.Error(mapApiException(response.code()))
            } else {
                DataState.Error(mapApiException(0))
            }
        }
    } catch (ex: Throwable) {
        DataState.Error(mapToNetworkError(ex))
    }
}

private fun mapApiException(code: Int): Exception {
    return when (code) {
        HttpURLConnection.HTTP_NOT_FOUND -> Failure.NotFoundException("")
        HttpURLConnection.HTTP_UNAUTHORIZED -> Failure.UnAuthorizedException
        else -> Failure.UnknownError
    }
}

private fun mapToNetworkError(t: Throwable): Exception {
    return when (t) {
        is SocketTimeoutException -> Failure.SocketTimeoutError("")
        is UnknownHostException -> Failure.UnknownHostError
        else -> Failure.UnknownError
    }
}

sealed class Failure : IOException() {
    object JsonError : Failure()
    object UnknownError : Failure()
    object UnknownHostError : Failure()
    object NoConnectivityError : Failure()
    object EmptyResponse : Failure()
    data class TimeOutError(override var message: String) : Failure()
    data class ApiError(var code: Int = 0, override var message: String) : Failure()
    data class ServerError(var code: Int = 0, override var message: String) : Failure()
    data class NotFoundException(override var message: String) : Failure()
    object UnAuthorizedException : Failure()
    data class SocketTimeoutError(override var message: String) : Failure()
    object NoInternetException : Failure()
}