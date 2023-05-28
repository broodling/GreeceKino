package com.android.domain.useCase

import com.android.data.repository.BettingRepository
import com.android.domain.useCase.base.BaseParams
import com.android.domain.useCase.base.BaseUseCase
import com.android.domain.useCase.params.ResultParams
import com.android.model.local.result.Result

class GetResultsUseCase(private val bettingRepository: BettingRepository) :
    BaseUseCase<BaseParams, List<Result>>() {

    operator fun invoke(params: ResultParams) = fromServer {
        bettingRepository.getResults(
            gameId = params.gameId,
            fromDate = params.fromDate,
            toDate = params.toDate
        ).content
    }
}
