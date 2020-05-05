package com.dionataferraz.remote.dsl

import androidx.lifecycle.MutableLiveData
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.core.internal.Resource
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CallHandler<RESPONSE : Any> {
    lateinit var client: Deferred<Response<RESPONSE>>

    fun makeCall(): MutableLiveData<Resource<RESPONSE>> {
        val result = MutableLiveData<Resource<RESPONSE>>()
        result.value = Resource.Loading()

        GlobalScope.launch {
            try {
                val response = client.await().body()
                withContext(Dispatchers.Main) {
                    result.value = Resource.Success(response)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    val networkError =
                        when (exception) {
                            is ConnectException, is UnknownHostException -> NetworkError(
                                NetworkErrorType.CONNECTION
                            )
                            is HttpException -> httpNetworkError(exception)
                            is SocketTimeoutException -> NetworkError(NetworkErrorType.TIME_OUT)
                            else -> NetworkError(NetworkErrorType.FAILURE)
                        }
                    result.postValue(Resource.Error(networkError))
                }
                exception.printStackTrace()
            }
        }

        return result
    }

    private fun httpNetworkError(exception: HttpException): NetworkError {
        if (exception.code() == 401) {
            return NetworkError(NetworkErrorType.UNAUTHORIZED)
        }
        return try {
            NetworkError(NetworkErrorType.RESPONSE, exception.code(), exception.message())
        } catch (exception: Exception) {
            NetworkError(NetworkErrorType.RESPONSE)
        }
    }

}

fun <RESPONSE : Any> networkCall(block: CallHandler<RESPONSE>.() -> Unit): MutableLiveData<Resource<RESPONSE>> =
    CallHandler<RESPONSE>().apply(block).makeCall()