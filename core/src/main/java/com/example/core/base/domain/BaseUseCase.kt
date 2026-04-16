package com.example.core.base.domain

import com.example.core.base.network.NetWorkCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<Type, Params> {

    abstract suspend fun execute(params: Params): Type

    operator fun invoke(params: Params): Flow<NetWorkCall<Type>> = flow {
        emit(NetWorkCall.Loading())
        val result = execute(params)
        emit(NetWorkCall.Success(result))

    }.catch { e ->
        /*
     * FLOW EXCEPTION TRANSPARENCY:
     * We must use '.catch' instead of a manual 'try-catch' block inside the flow builder.
     * Manual try-catch can accidentally intercept internal 'CancellationException' or
     * 'AbortFlowException' (thrown by operators like .first() or .take(1)).
     * Intercepting these signals and attempting to 'emit' a value violates Flow transparency
     * and triggers an IllegalStateException. The '.catch' operator correctly ignores
     * cancellation signals while handling actual upstream exceptions.
     */
        emit(NetWorkCall.Error(e.message ?: "Unknown error"))
    }
}