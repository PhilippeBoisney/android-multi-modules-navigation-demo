package com.backmarket.pocnavigation.navigation.direction

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Convenient interface that provides more context for a [ScreenDirection].
 *
 * Use it in order to pass [extra] to the screen you want to launch.
 */
@Parcelize
data class NavDirectionContext(
    val extra: Parcelable = Bundle.EMPTY
) : Parcelable {

    companion object {
        internal const val CONTEXT_PARAM = "PARAM_NAV_DIRECTION_CONTEXT"
    }
}
