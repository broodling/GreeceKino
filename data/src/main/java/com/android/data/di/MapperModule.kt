package com.android.data.di

import com.android.data.mappers.AddOnMapper
import com.android.data.mappers.PricePointMapper
import com.android.data.mappers.PrizeCategoryMapper
import com.android.data.mappers.ResultListMapper
import com.android.data.mappers.ResultMapper
import com.android.data.mappers.UpcomingGameDrawMapper
import com.android.data.mappers.UpcomingGamesListMapper
import com.android.data.mappers.WagerStatisticMapper
import com.android.data.mappers.WinningNumbersMapper
import org.koin.dsl.module


val mapperModule = module {
    factory { AddOnMapper() }
    factory { PricePointMapper() }
    factory { PrizeCategoryMapper() }
    factory { WagerStatisticMapper(get()) }
    factory { UpcomingGameDrawMapper(get(), get(), get()) }
    factory { UpcomingGamesListMapper(get()) }
    factory { ResultListMapper(get()) }
    factory { ResultMapper(get(), get(), get(), get()) }
    factory { WinningNumbersMapper() }
}
