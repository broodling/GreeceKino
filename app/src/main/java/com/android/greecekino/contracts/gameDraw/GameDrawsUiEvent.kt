package com.android.greecekino.contracts.gameDraw

import com.android.greecekino.contracts.UiEvent
import com.android.model.local.upcoming.UpcomingGameDraw

sealed class GameDrawsUiEvent : UiEvent {
    object SetIdleState : GameDrawsUiEvent()
    object GetUpcomingGames : GameDrawsUiEvent()
    data class OnGameDrawClick(val upcomingGameDraw: UpcomingGameDraw) : GameDrawsUiEvent()
}