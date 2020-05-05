package com.dionataferraz.marvel_comics.extensions

import android.view.View
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.marvel_comics.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(networkError: NetworkError?) {
    networkError?.let {
        Snackbar.make(this, getMessageError(it.networkErrorType), Snackbar.LENGTH_LONG).run {
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
