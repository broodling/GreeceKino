package com.android.greecekino.contracts.gameDetails

import com.android.greecekino.contracts.UiEvent

sealed class GameDetailsUiEvent : UiEvent {
    object PopulateViewPager : GameDetailsUiEvent()
}