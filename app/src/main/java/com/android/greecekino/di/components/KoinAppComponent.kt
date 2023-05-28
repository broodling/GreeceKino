package com.android.greecekino.di.components

import com.android.data.di.mapperModule
import com.android.data.di.repositoryModule
import com.android.domain.di.useCaseModule
import com.android.greecekino.di.modules.viewModelModule
import com.android.network.networkModule

val koinAppComponents = listOf(
    networkModule,
    repositoryModule,
    mapperModule,
    useCaseModule,
    viewModelModule
)