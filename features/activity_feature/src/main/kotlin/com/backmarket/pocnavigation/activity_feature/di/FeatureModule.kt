package com.backmarket.pocnavigation.activity_feature.di

import com.backmarket.pocnavigation.activity_feature.model.FeatureViewModel
import com.backmarket.pocnavigation.activity_feature.model.FeatureViewModelImpl
import com.backmarket.pocnavigation.activity_feature.navigation.FeatureActivityDirectionImpl
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureActivityModule = module {

    factory<FeatureActivityDirection> { (params: FeatureActivityDirection.Params) ->
        FeatureActivityDirectionImpl(
            context = androidContext(),
            params = params
        )
    }

    viewModel<FeatureViewModel> { (params: FeatureActivityDirection.Params) ->
        FeatureViewModelImpl(params)
    }
}
