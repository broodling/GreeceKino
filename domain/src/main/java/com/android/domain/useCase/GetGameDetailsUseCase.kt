package com.android.domain.useCase

import com.android.data.repository.BettingRepository
import com.android.domain.useCase.base.BaseParams
import com.android.domain.useCase.base.BaseUseCase
import com.android.domain.useCase.params.GameDetailsParams
import com.android.model.local.upcoming.UpcomingGameDraw

class GetGameDetailsUseCase(private val bettingRepository: BettingRepository) :
    BaseUseCase<BaseParams, UpcomingGameDraw>() {

    operator fun invoke(params: GameDetailsParams) = fromServer {
        bettingRepository.getGameDetails(gameId = params.gameId, drawId = params.drawId)
    }
}