package com.android.greecekino.contracts.gameDraw

import com.android.greecekino.contracts.UiState
import com.android.model.local.upcoming.UpcomingGameDraw

sealed class GameDrawsUiState : UiState {
    object Idle : GameDrawsUiState()
    object Loading : GameDrawsUiState()
    data class ShowGameDraws(val draws: List<UpcomingGameDraw>) : GameDrawsUiState()
}
