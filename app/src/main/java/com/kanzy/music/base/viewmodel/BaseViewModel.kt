package com.kanzy.music.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanzy.data.remote.DataState
import kotlinx.coroutines.flow.*

// todo: sonradan düzenleme yapılacak.
open class BaseViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean?>(null)
    val isLoading get() = _isLoading.asStateFlow()

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    open fun showLoading() {
        _isLoading.value = true
    }

    open fun hideLoading() {
        _isLoading.value = false
    }

    open fun passError(throwable: Throwable, showSystemError: Boolean = true) {
        if (showSystemError)
            _error.value = throwable
    }

    protected suspend fun <T> call(
        callFlow: Flow<DataState<T>>,
        showSystemError: Boolean = true,
        success: (data: T) -> Unit,
        error: (error: Throwable) -> Unit
    ) {
        callFlow
            .catch {
                error(it)
                passError(it, showSystemError)
            }
            .collect { state ->
                when (state) {
                    is DataState.Success -> {
                        success(state.response)
                    }
                    is DataState.Error -> {
                        error(state.error)
                        passError(state.error, showSystemError)
                    }
                }
            }
    }

    protected suspend fun <T> execute(
        callFlow: Flow<T>,
        completionHandler: (data: T) -> Unit
    ) {
        callFlow
            .catch { passError(it) }
            .collect { completionHandler(it) }
    }

    protected suspend fun <T> executeApi(
        callFlow: Flow<DataState<T>>,
        showLoading: Boolean = true,
        completionHandler: (data: T) -> Unit
    ) {
        if (showLoading) {
            executeApiWithLoading(callFlow, completionHandler)
        } else {
            executeApiNoLoading(callFlow, completionHandler)
        }
    }

    private suspend fun <T> executeApiNoLoading(
        callFlow: Flow<DataState<T>>,
        completionHandler: (data: T) -> Unit
    ) {
        callFlow
            .catch { passError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Success -> {
                        completionHandler(state.response)
                    }
                    is DataState.Error -> {
                        passError(state.error)
                    }
                }
            }
    }

    private suspend fun <T> executeApiWithLoading(
        callFlow: Flow<DataState<T>>,
        completionHandler: (data: T) -> Unit
    ) {
        callFlow
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .catch { passError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Success -> {
                        completionHandler(state.response)
                    }
                    is DataState.Error -> {
                        passError(state.error)
                    }
                }
            }
    }

}