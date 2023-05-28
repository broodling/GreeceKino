package com.android.data.repository

import com.android.model.local.result.ResultList
import com.android.model.local.upcoming.UpcomingGameDraw

interface BettingRepository {
    suspend fun getUpcomingGames(gameId: String, count: String): List<UpcomingGameDraw>
    suspend fun getGameDetails(gameId: String, drawId: String): UpcomingGameDraw
    suspend fun getResults(gameId: String, fromDate: String, toDate: String): ResultList
}