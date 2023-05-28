package com.android.model.remote.result

import com.android.model.remote.PricePointRemote
import com.android.model.remote.PrizeCategoryRemote
import com.android.model.remote.WagerStatisticsRemote
import com.google.gson.annotations.SerializedName

data class ResultRemote(
    @SerializedName("gameId")
    val gameId: Int?,
    @SerializedName("drawId")
    val drawId: Int?,
    @SerializedName("drawTime")
    val drawTime: Long?,
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("drawBreak")
    val drawBreak: Int?,
    @SerializedName("visualDraw")
    val visualDraw: Long?,
    @SerializedName("pricePoints")
    val pricePoints: PricePointRemote?,
    @SerializedName("prizeCategories")
    val prizeCategories: List<PrizeCategoryRemote>?,
    @SerializedName("wagerStatistics")
    val wagerStatistics: WagerStatisticsRemote?,
    @SerializedName("winningNumbers")
    val winningNumbers: WinningNumbersRemote?

)