package com.android.greecekino.contracts.gameDetails.talon

import com.android.greecekino.contracts.UiEvent

sealed class TalonUiEvent : UiEvent {
    data class GetDrawGameDetails(val gameId: String, val drawId: String) : TalonUiEvent()
    data class UpdateNumbersCount(val numbersCount: Int) : TalonUiEvent()
}