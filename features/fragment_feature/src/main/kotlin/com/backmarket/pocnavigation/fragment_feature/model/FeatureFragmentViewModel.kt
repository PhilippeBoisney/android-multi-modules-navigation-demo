package com.backmarket.pocnavigation.fragment_feature.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.backmarket.pocnavigation.core.MutableLiveEvent
import com.backmarket.pocnavigation.navigation.NavigableViewModel
import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureFragmentDirection
import com.backmarket.pocnavigation.navigation.model.NavigationCommand

abstract class FeatureFragmentViewModel : ViewModel(), NavigableViewModel<NavDirection> {
    abstract fun onUserClicksOnBack()
}

class FeatureFragmentViewModelImpl(
    params: FeatureFragmentDirection.Params
) : FeatureFragmentViewModel() {

    init {
        Log.d("TAG", "Params for this screen is: $params")
    }

    override val router = MutableLiveEvent<NavigationCommand>()

    override fun onUserClicksOnBack() {
        router.navigateBack()
    }
}
