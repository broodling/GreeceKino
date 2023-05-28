package com.android.greecekino.di.modules

import com.android.greecekino.screens.gameDetails.GameDetailsViewModel
import com.android.greecekino.screens.gameDetails.results.ResultsViewModel
import com.android.greecekino.screens.gameDetails.talon.TalonViewModel
import com.android.greecekino.screens.gameDraws.GameDrawsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GameDrawsViewModel(get()) }
    viewModel { TalonViewModel(get()) }
    viewModel { GameDetailsViewModel() }
    viewModel { ResultsViewModel(get()) }
}