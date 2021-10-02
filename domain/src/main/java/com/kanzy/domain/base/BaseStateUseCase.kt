package com.kanzy.domain.base

import com.kanzy.data.remote.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseStateUseCase<in Params, Result> {

    protected abstract fun execute(params: Params): Flow<DataState<Result>>

    operator fun invoke(params: Params): Flow<DataState<Result>> = execute(params)
        .flowOn(Dispatchers.IO)

}