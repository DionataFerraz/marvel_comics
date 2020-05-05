package com.dionataferraz.presentation.data

import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType

object ErrorData {

    val CONNECTION = NetworkError(NetworkErrorType.CONNECTION)
    val FAILURE = NetworkError(NetworkErrorType.FAILURE)
    val RESPONSE = NetworkError(NetworkErrorType.RESPONSE)
    val TIME_OUT = NetworkError(NetworkErrorType.TIME_OUT)
    val UNAUTHORIZED = NetworkError(NetworkErrorType.UNAUTHORIZED)
}