package com.dionataferraz.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

inline fun <reified X, Y> switchMapToLiveData(
    currentLiveData: LiveData<Y>,
    noinline block: (Y) -> X
): LiveData<X> = Transformations.switchMap(currentLiveData) {
    MutableLiveData<X>(block(it))
}