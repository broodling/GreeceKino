package com.android.greecekino.contracts.gameDetails

import com.android.greecekino.contracts.UiState

sealed class GameDetailsUiState : UiState {
    object Idle : GameDetailsUiState()
    object Loading : GameDetailsUiState()
    object ShowScreens : GameDetailsUiState()
}
