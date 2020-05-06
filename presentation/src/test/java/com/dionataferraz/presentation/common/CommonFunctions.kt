package com.dionataferraz.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.NetworkError
import junit.framework.Assert.assertEquals

private val observerString = Observer<String?> {}
private val observerBoolean = Observer<Boolean?> {}
private val observerNetworkError = Observer<NetworkError?> {}

fun verifyString(liveDataString: LiveData<String?>, valueExpcted: String) {
    liveDataString.run {
        try {
            observeForever(observerString)
            assertEquals(value, valueExpcted)
        } finally {
            removeObserver(observerString)
        }
    }
}

fun verifyStringNotNull(liveDataString: LiveData<String>, valueExpcted: String) {
    liveDataString.run {
        try {
            observeForever(observerString)
            assertEquals(value, valueExpcted)
        } finally {
            removeObserver(observerString)
        }
    }
}

fun verifyBooleanNotNull(liveDataString: LiveData<Boolean>, valueExpcted: Boolean) {
    liveDataString.run {
        try {
            observeForever(observerBoolean)
            assertEquals(value, valueExpcted)
        } finally {
            removeObserver(observerBoolean)
        }
    }
}

fun verifyBoolean(liveDataString: LiveData<Boolean?>, valueExpcted: Boolean) {
    liveDataString.run {
        try {
            observeForever(observerBoolean)
            assertEquals(value, valueExpcted)
        } finally {
            removeObserver(observerBoolean)
        }
    }
}

fun verifyError(error: MediatorLiveData<NetworkError>, valueExpcted: NetworkError) {
    error.run {
        try {
            observeForever(observerNetworkError)
            assertEquals(value, valueExpcted)
        } finally {
            removeObserver(observerNetworkError)
        }
    }
}