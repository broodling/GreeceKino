package com.android.data.repository

import com.android.data.mappers.ResultListMapper
import com.android.data.mappers.ResultMapper
import com.android.data.mappers.UpcomingGameDrawMapper
import com.android.data.mappers.UpcomingGamesListMapper
import com.android.model.local.result.ResultList
import com.android.network.ApiService

class BettingDataRepository(
    private val upcomingGamesListMapper: UpcomingGamesListMapper,
    private val upcomingGameDrawMapper: UpcomingGameDrawMapper,
    private val resultListMapper: ResultListMapper,
    private val apiService: ApiService
) : BettingRepository {

    override suspend fun getUpcomingGames(gameId: String, count: String) =
        upcomingGamesListMapper.map(apiService.getUpcomingGameDraws(gameId = gameId, count = count))

    override suspend fun getGameDetails(gameId: String, drawId: String) =
        upcomingGameDrawMapper.map(
            apiService.getSingleGameDetails(
                gameId = gameId, drawId = drawId
            )
        )

    override suspend fun getResults(gameId: String, fromDate: String, toDate: String) =
        resultListMapper.map(
            apiService.getResults(
                gameId = gameId, fromDate = fromDate, toDate = toDate
            )
        )
}
