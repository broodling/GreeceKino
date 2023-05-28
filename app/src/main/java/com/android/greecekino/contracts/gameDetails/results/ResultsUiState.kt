package com.android.greecekino.contracts.gameDetails.results

import com.android.greecekino.contracts.UiState
import com.android.model.local.result.Result

sealed class ResultsUiState : UiState {
    object Idle : ResultsUiState()
    object Loading : ResultsUiState()
    data class ShowPreviousResults(val results: List<Result>) : ResultsUiState()
}