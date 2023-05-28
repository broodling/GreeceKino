package com.android.domain.useCase

import com.android.data.repository.BettingRepository
import com.android.domain.useCase.base.BaseParams
import com.android.domain.useCase.base.BaseUseCase
import com.android.domain.useCase.params.UpcomingGamesParams
import com.android.model.local.upcoming.UpcomingGameDraw

class GetUpcomingGamesUseCase(private val bettingRepository: BettingRepository) :
    BaseUseCase<BaseParams, List<UpcomingGameDraw>>() {

    operator fun invoke(upcomingGamesParams: UpcomingGamesParams) = fromServer {
        bettingRepository.getUpcomingGames(
            gameId = upcomingGamesParams.gameId,
            count = upcomingGamesParams.count
        )
    }

}
