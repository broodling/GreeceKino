package com.android.data.mappers

import com.android.model.local.upcoming.UpcomingGameDraw
import com.android.model.remote.upcoming.UpcomingGameDrawRemote

class UpcomingGamesListMapper(private val upcomingGameDrawMapper: UpcomingGameDrawMapper) :
    BaseMapper<List<UpcomingGameDrawRemote>?, List<UpcomingGameDraw>> {

    override fun map(input: List<UpcomingGameDrawRemote>?): List<UpcomingGameDraw> {
        val upcomingGames: MutableList<UpcomingGameDraw> = mutableListOf()
        input?.let { upcomingGamesRemote ->
            for (upcomingGame in upcomingGamesRemote) {
                upcomingGames.add(upcomingGameDrawMapper.map(upcomingGame))
            }
        }
        return upcomingGames
    }
}
