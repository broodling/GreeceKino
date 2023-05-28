package com.android.greecekino.contracts.gameDetails.results

import com.android.greecekino.contracts.UiEvent

sealed class ResultsUiEvent : UiEvent {
    data class GetPreviousResults(val gameId: String, val fromDate: String, val toDate: String) :
        ResultsUiEvent()
}