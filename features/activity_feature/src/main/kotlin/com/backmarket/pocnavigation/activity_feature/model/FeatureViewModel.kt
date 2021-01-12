package com.backmarket.pocnavigation.activity_feature.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backmarket.pocnavigation.core.MutableLiveEvent
import com.backmarket.pocnavigation.navigation.NavigableViewModel
import com.backmarket.pocnavigation.navigation.direction.NavDirection
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection
import com.backmarket.pocnavigation.navigation.model.NavigationCommand

abstract class FeatureViewModel : ViewModel(), NavigableViewModel<NavDirection> {
    abstract val isOpenForResult: LiveData<Boolean>

    abstract fun onUserClicksOnFinish()
    abstract fun onUserClicksOnFinishWithResult()
    abstract fun onUserClicksOnBack()
}

class FeatureViewModelImpl(
    params: FeatureActivityDirection.Params
) : FeatureViewModel() {

    init {
        Log.d("TAG", "Params for this screen is: $params")
    }

    override val isOpenForResult = MutableLiveData(params.enableFinishForResult)
    override val router = MutableLiveEvent<NavigationCommand>()

    override fun onUserClicksOnFinish() {
        router.finish()
    }

    override fun onUserClicksOnFinishWithResult() {
        val someRandomResult = FeatureActivityDirection.Returns("Hello world!")
        router.finish(someRandomResult)
    }

    override fun onUserClicksOnBack() {
        router.navigateBack()
    }
}
