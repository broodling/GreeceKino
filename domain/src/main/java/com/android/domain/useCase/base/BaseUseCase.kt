package com.android.domain.useCase.base

import com.android.model.networkData.NetworkResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<P: BaseParams, T> {


    fun fromServer(
        apiCode: String? = null,
        onRequest: suspend () -> T,
    ): Flow<NetworkResultData<T>> = flow {
        val data = onRequest()
        emit(NetworkResultData.Success(data))
    }.onStart {
        emit(NetworkResultData.Loading())
    }.catch {
        emit(NetworkResultData.Error(throwable = it, apiCode = apiCode))
    }
}
