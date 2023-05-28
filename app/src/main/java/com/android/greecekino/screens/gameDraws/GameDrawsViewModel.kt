package com.android.greecekino.screens.gameDraws

import androidx.lifecycle.viewModelScope
import com.android.domain.useCase.GetUpcomingGamesUseCase
import com.android.domain.useCase.params.UpcomingGamesParams
import com.android.greecekino.contracts.gameDraw.GameDrawsUiEffect
import com.android.greecekino.contracts.gameDraw.GameDrawsUiEvent
import com.android.greecekino.contracts.gameDraw.GameDrawsUiState
import com.android.greecekino.viewModel.BaseViewModel
import com.android.model.networkData.Status.ERROR
import com.android.model.networkData.Status.LOADING
import com.android.model.networkData.Status.SUCCESS
import kotlinx.coroutines.launch


class GameDrawsViewModel(val getUpcomingGamesUseCase: GetUpcomingGamesUseCase) :
    BaseViewModel<GameDrawsUiEvent, GameDrawsUiState, GameDrawsUiEffect>() {

    override fun createInitialState() = GameDrawsUiState.Idle
    override fun handleEvent(event: GameDrawsUiEvent) {
        when (event) {
            is GameDrawsUiEvent.GetUpcomingGames -> getUpcomingGames()
            is GameDrawsUiEvent.OnGameDrawClick -> setEffect(GameDrawsUiEffect.GoToGameDetails(event.upcomingGameDraw))
            else -> {}
        }
    }

    private fun getUpcomingGames() {
        viewModelScope.launch(ioContext()) {
            getUpcomingGamesUseCase(
                UpcomingGamesParams(
                    gameId = "1100",
                    count = "20"
                )
            ).collect { result ->
                when (result.status) {
                    SUCCESS -> result.data?.let {
                        setState(GameDrawsUiState.ShowGameDraws(it))
                    }

                    LOADING -> setState(GameDrawsUiState.Loading)
                    ERROR -> {}
                }
            }
        }
    }
}