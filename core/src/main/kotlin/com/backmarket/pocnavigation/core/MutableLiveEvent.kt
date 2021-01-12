package com.backmarket.pocnavigation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

typealias LiveEvent<T> = LiveData<Event<T>>

/**
 * Create an event with the provided [value] and set the value of this [MutableLiveEvent]
 */
fun <T> MutableLiveEvent<T>.publish(value: T? = null) {
    this.value = Event(value)
}
