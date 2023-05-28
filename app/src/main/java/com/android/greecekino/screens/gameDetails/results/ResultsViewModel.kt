package com.android.greecekino.screens.gameDetails.results

import androidx.lifecycle.viewModelScope
import com.android.domain.useCase.GetResultsUseCase
import com.android.domain.useCase.params.ResultParams
import com.android.greecekino.contracts.gameDetails.results.ResultsUiEffect
import com.android.greecekino.contracts.gameDetails.results.ResultsUiEvent
import com.android.greecekino.contracts.gameDetails.results.ResultsUiState
import com.android.greecekino.viewModel.BaseViewModel
import com.android.model.networkData.Status
import kotlinx.coroutines.launch

class ResultsViewModel(val getResultsUseCase: GetResultsUseCase) :
    BaseViewModel<ResultsUiEvent, ResultsUiState, ResultsUiEffect>() {

    override fun createInitialState() = ResultsUiState.Idle

    override fun handleEvent(event: ResultsUiEvent) {
        when (event) {
            is ResultsUiEvent.GetPreviousResults -> getPreviousResults(
                event.gameId,
                event.fromDate,
                event.toDate
            )
        }
    }

    private fun getPreviousResults(gameId: String, fromDate: String, toDate: String) {
        viewModelScope.launch(ioContext()) {
            getResultsUseCase(
                ResultParams(
                    gameId = gameId, fromDate = fromDate, toDate = toDate
                )
            ).collect { result ->
                when (result.status) {
                    Status.SUCCESS -> result.data?.let {
                        setState(ResultsUiState.ShowPreviousResults(it))
                    }

                    Status.LOADING -> setState(ResultsUiState.Loading)
                    Status.ERROR -> {}
                }
            }
        }
    }
}
