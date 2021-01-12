package com.backmarket.pocnavigation.fragment_feature.navigation

import com.backmarket.pocnavigation.fragment_feature.FeatureFragment
import com.backmarket.pocnavigation.navigation.direction.NavDirectionContext
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureFragmentDirection

class FeatureFragmentDirectionImpl(
    params: FeatureFragmentDirection.Params
) : FeatureFragmentDirection {
    override val navContext = NavDirectionContext(extra = params)
    override fun toScreen() = FeatureFragment.newInstance()
}
