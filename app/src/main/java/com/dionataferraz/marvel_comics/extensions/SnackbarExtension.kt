package com.dionataferraz.marvel_comics.extensions

import android.app.Activity
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.marvel_comics.R
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackbar(networkError: NetworkError?) {
    networkError?.let {
        Snackbar.make(findViewById(android.R.id.content), getMessageError(it.networkErrorType), Snackbar.LENGTH_LONG).run {
            setAction(R.string.snackbar_ok) {
                dismiss()
            }
        }.show()
    }
}

private fun getMessageError(networkErrorType: NetworkErrorType): Int =
    when (networkErrorType) {
        NetworkErrorType.CONNECTION -> R.string.error_message_connection
        NetworkErrorType.UNAUTHORIZED -> R.string.error_message_unauthorized
        NetworkErrorType.RESPONSE -> R.string.error_message_response
        NetworkErrorType.TIME_OUT -> R.string.error_message_time_out
        NetworkErrorType.FAILURE -> R.string.error_message_failure
    }
