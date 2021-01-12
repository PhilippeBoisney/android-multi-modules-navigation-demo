package com.backmarket.pocnavigation.activity_feature.navigation

import android.content.Context
import android.content.Intent
import com.backmarket.pocnavigation.activity_feature.FeatureActivity
import com.backmarket.pocnavigation.navigation.direction.NavDirectionContext
import com.backmarket.pocnavigation.navigation.direction.Screen
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection

class FeatureActivityDirectionImpl(
    private val context: Context,
    params: FeatureActivityDirection.Params
) : FeatureActivityDirection {

    override val navContext = NavDirectionContext(extra = params)
    override fun toScreen(): Screen = Intent(context, FeatureActivity::class.java)
}
