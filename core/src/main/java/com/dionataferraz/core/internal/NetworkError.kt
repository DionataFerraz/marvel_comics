package com.dionataferraz.core.internal

data class NetworkError(
    val networkErrorType: NetworkErrorType,
    val code: Int? = null,
    val error: String? = null
)

enum class NetworkErrorType {

    CONNECTION,
    UNAUTHORIZED,
    RESPONSE,
    TIME_OUT,
    FAILURE

}