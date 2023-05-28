package com.android.greecekino.contracts.gameDetails.talon

import com.android.greecekino.contracts.UiState
import com.android.model.local.upcoming.UpcomingGameDraw

sealed class TalonUiState : UiState {
    object Idle : TalonUiState()
    object Loading : TalonUiState()
    data class ShowSelectedNumbersCount(val numbersCount: Int) : TalonUiState()
    data class ShowTalon(val upcomingGameDraw: UpcomingGameDraw) : TalonUiState()
}
