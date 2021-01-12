package com.backmarket.pocnavigation.core

import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner

/**
 * Returns a casted [LifecycleOwner] from a [ComponentActivity].
 * It may be easier to read than just referencing `this` in some cases.
 */
inline val ComponentActivity.lifecycleOwner: LifecycleOwner
    get() = this
