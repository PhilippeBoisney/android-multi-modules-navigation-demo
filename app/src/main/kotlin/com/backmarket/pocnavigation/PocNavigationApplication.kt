package com.backmarket.pocnavigation

import android.app.Application
import com.backmarket.pocnavigation.activity_feature.di.featureActivityModule
import com.backmarket.pocnavigation.fragment_feature.di.featureFragmentModule
import com.backmarket.pocnavigation.home.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PocNavigationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() = startKoin {
        androidContext(this@PocNavigationApplication)
        modules(mainModule, featureActivityModule, featureFragmentModule)
    }
}
