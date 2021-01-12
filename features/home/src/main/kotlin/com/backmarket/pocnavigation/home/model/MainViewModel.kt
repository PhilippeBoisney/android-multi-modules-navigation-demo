package com.backmarket.pocnavigation.home.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backmarket.pocnavigation.core.MutableLiveEvent
import com.backmarket.pocnavigation.home.navigation.HomeDirections
import com.backmarket.pocnavigation.navigation.NavigableViewModel
import com.backmarket.pocnavigation.navigation.direction.screen.FeatureActivityDirection
import com.backmarket.pocnavigation.navigation.model.NavigationCommand
import kotlinx.coroutines.launch

abstract class MainViewModel : ViewModel(), NavigableViewModel<HomeDirections> {
    abstract fun onUserClicksOnActivity()
    abstract fun onUserClicksOnActivityForResult()
    abstract fun onUserClicksOnFragment()
}

class MainViewModelImpl : MainViewModel() {

    override val router = MutableLiveEvent<NavigationCommand>()

    override fun onUserClicksOnActivity() {
        router.navigateTo(HomeDirections.FeatureActivity(someFakeId))
    }

    override fun onUserClicksOnActivityForResult() {
        viewModelScope.launch {
            val result = router.navigateForResult(HomeDirections.FeatureActivity(someFakeId, true))
            if (result.hasData) {
                val returns = result.getParcelable<FeatureActivityDirection.Returns>()
                Log.d("TAG", "Result is $returns")
            } else {
                Log.d("TAG", "No result")
            }
        }
    }

    override fun onUserClicksOnFragment() {
        router.navigateTo(HomeDirections.FeatureFragment(someFakeId))
    }

    companion object {
        private const val someFakeId = "1234"
    }
}
