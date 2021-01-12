package com.backmarket.pocnavigation.fragment_feature.di

import com.backmarket.pocnavigation.fragment_feature.model.FeatureFragmentViewModel
import com.backmarket.pocnavigation.fragment_feature.model.FeatureFragmentViewModelImpl
import com.backmarket.pocnavigation.fragment_feature.navigation.FeatureFragmentDirectionImpl
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureFragmentDirection
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureFragmentModule = module {

    factory<FeatureFragmentDirection> { (params: FeatureFragmentDirection.Params) ->
        FeatureFragmentDirectionImpl(
            params = params
        )
    }

    viewModel<FeatureFragmentViewModel> { (params: FeatureFragmentDirection.Params) ->
        FeatureFragmentViewModelImpl(
            params = params
        )
    }
}
