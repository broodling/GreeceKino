package com.android.greecekino.screens.gameDetails.talon

import androidx.lifecycle.viewModelScope
import com.android.domain.useCase.GetGameDetailsUseCase
import com.android.domain.useCase.params.GameDetailsParams
import com.android.domain.useCase.params.UpcomingGamesParams
import com.android.greecekino.contracts.gameDetails.talon.TalonUiEffect
import com.android.greecekino.contracts.gameDetails.talon.TalonUiEvent
import com.android.greecekino.contracts.gameDetails.talon.TalonUiState
import com.android.greecekino.contracts.gameDraw.GameDrawsUiState
import com.android.greecekino.viewModel.BaseViewModel
import com.android.model.networkData.Status
import kotlinx.coroutines.launch

class TalonViewModel(val getGameDetailsUseCase: GetGameDetailsUseCase) :
    BaseViewModel<TalonUiEvent, TalonUiState, TalonUiEffect>() {

    override fun createInitialState() = TalonUiState.Idle

    override fun handleEvent(event: TalonUiEvent) {
        when (event) {
            is TalonUiEvent.GetDrawGameDetails -> getGameDetails(
                gameId = event.gameId,
                drawId = event.drawId
            )

            is TalonUiEvent.UpdateNumbersCount -> setState(
                TalonUiState.ShowSelectedNumbersCount(
                    event.numbersCount
                )
            )
        }
    }

    private fun getGameDetails(gameId: String, drawId: String) {
        viewModelScope.launch(ioContext()) {
            getGameDetailsUseCase(
                GameDetailsParams(
                    gameId = gameId,
                    drawId = drawId
                )
            ).collect { result ->
                when (result.status) {
                    Status.SUCCESS -> result.data?.let {
                        setState(TalonUiState.ShowTalon(it))
                    }

                    Status.LOADING -> setState(TalonUiState.Loading)
                    Status.ERROR -> {}
                }
            }
        }
    }
}