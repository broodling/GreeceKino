package com.android.data.di

import com.android.data.repository.BettingDataRepository
import com.android.data.repository.BettingRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BettingRepository> {
        BettingDataRepository(get(), get(), get(), get())
    }
}