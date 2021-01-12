package com.backmarket.pocnavigation.home.di

import com.backmarket.pocnavigation.home.model.MainViewModel
import com.backmarket.pocnavigation.home.model.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel<MainViewModel> {
        MainViewModelImpl()
    }
}
