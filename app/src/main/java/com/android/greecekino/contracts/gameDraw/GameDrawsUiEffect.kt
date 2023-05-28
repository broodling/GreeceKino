package com.android.greecekino.contracts.gameDraw

import com.android.greecekino.contracts.UiEffect
import com.android.model.local.upcoming.UpcomingGameDraw

sealed class GameDrawsUiEffect : UiEffect {
    data class GoToGameDetails(val upcomingGameDraw: UpcomingGameDraw) : GameDrawsUiEffect()
    data class ShowError(val message: String) : GameDrawsUiEffect()
}