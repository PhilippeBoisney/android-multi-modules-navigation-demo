package com.backmarket.pocnavigation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    observe(owner) {
        if (it != null) {
            observer.invoke(it)
        }
    }
}

fun <T> LiveData<Event<T>>.observeAndConsumeNonNull(
    owner: LifecycleOwner,
    block: (T) -> Unit
) {
    observeNonNull(owner) {
        it.consumeAndRunNonNull(block)
    }
}
