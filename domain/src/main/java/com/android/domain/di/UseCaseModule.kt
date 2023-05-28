package com.android.domain.di

import com.android.domain.useCase.GetGameDetailsUseCase
import com.android.domain.useCase.GetResultsUseCase
import com.android.domain.useCase.GetUpcomingGamesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUpcomingGamesUseCase(get()) }
    factory { GetGameDetailsUseCase(get()) }
    factory { GetResultsUseCase(get()) }
}