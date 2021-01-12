package com.backmarket.pocnavigation.home.navigation

import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.direction.getNavImpl
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureFragmentDirection

sealed class HomeDirections : NavDirection {
    data class FeatureActivity(
        private val id: String,
        private val enableFinishForResult: Boolean = false
    ) : HomeDirections(), FeatureActivityDirection by getNavImpl(
        FeatureActivityDirection.Params(id, enableFinishForResult)
    )
    data class FeatureFragment(
        private val id: String
    ) : HomeDirections(), FeatureFragmentDirection by getNavImpl(
        FeatureFragmentDirection.Params(id)
    )
}
