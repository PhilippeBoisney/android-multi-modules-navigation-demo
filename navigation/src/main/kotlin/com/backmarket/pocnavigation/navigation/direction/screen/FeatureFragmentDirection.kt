package com.backmarket.pocnavigation.navigation.direction.screen

import android.os.Parcelable
import com.backmarket.pocnavigation.navigation.direction.ScreenDirection
import kotlinx.android.parcel.Parcelize

interface FeatureFragmentDirection : ScreenDirection {
    @Parcelize
    data class Params(
        val id: String
    ) : Parcelable
}
