package com.example.marvelapp.data.source.remote

import com.example.marvelapp.repository.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <ResultType, RequestType> provideData(
    loadDb: () -> Flow<ResultType?>,
    removeDb: suspend () -> Unit,
    createCall: suspend () -> Response<RequestType>,
    shouldFetch: (ResultType?) -> Boolean,
    saveCallResult: suspend (RequestType) -> Unit
): Flow<Resource<ResultType?>> = flow {

    emit(Resource.loading(null))
    val cacheData = loadDb.invoke().firstOrNull()
    if (shouldFetch(cacheData)) {
        emit(Resource.loading(cacheData))
        try {
            val apiResponse = createCall.invoke()
            val remoteResponse = apiResponse.body()
            if (apiResponse.isSuccessful && remoteResponse != null) {
                removeDb()
                saveCallResult(remoteResponse)
                emitAll(loadDb().map { Resource.success(it) })
            } else {
                emitAll(loadDb().map { Resource.error(apiResponse.errorBody().toString(), it) })
            }
        } catch (exception: HttpException) {
            emit(Resource.error(exception.message(), null))
        } catch (exception: IOException) {
            emit(Resource.error(exception.message!!, null))
        }

    } else {
        emitAll(loadDb().map { Resource.success(it) })
    }
}