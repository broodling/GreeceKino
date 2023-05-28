package com.android.greecekino.screens.gameDetails

import com.android.greecekino.contracts.gameDetails.GameDetailsUiEffect
import com.android.greecekino.contracts.gameDetails.GameDetailsUiEvent
import com.android.greecekino.contracts.gameDetails.GameDetailsUiState
import com.android.greecekino.viewModel.BaseViewModel

class GameDetailsViewModel() :
    BaseViewModel<GameDetailsUiEvent, GameDetailsUiState, GameDetailsUiEffect>() {

    override fun createInitialState() = GameDetailsUiState.Idle

    override fun handleEvent(event: GameDetailsUiEvent) {
        when (event) {
            is GameDetailsUiEvent.PopulateViewPager -> setState(GameDetailsUiState.ShowScreens)
        }
    }
}