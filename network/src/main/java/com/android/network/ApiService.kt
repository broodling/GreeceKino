package com.android.network

import com.android.model.remote.result.ResultListRemote
import com.android.model.remote.upcoming.UpcomingGameDrawRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{gameId}/upcoming/{count}")
    suspend fun getUpcomingGameDraws(
        @Path("gameId") gameId: String,
        @Path("count") count: String
    ): List<UpcomingGameDrawRemote>

    @GET("{gameId}/{drawId}")
    suspend fun getSingleGameDetails(
        @Path("gameId") gameId: String,
        @Path("drawId") drawId: String
    ): UpcomingGameDrawRemote

    @GET("{gameId}/draw-date/{fromDate}/{toDate}")
    suspend fun getResults(
        @Path("gameId") gameId: String,
        @Path("fromDate") fromDate: String,
        @Path("toDate") toDate: String
    ): ResultListRemote

}