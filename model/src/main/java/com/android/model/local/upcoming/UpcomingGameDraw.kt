package com.android.model.local.upcoming

import com.android.model.local.PricePoint
import com.android.model.local.PrizeCategory
import com.android.model.local.WagerStatistic

class UpcomingGameDraw(
    val gameId: Int,
    val drawId: Int,
    val drawTime: Long,
    val status: Boolean,
    val drawBreak: Int,
    val visualDraw: Long,
    val pricePoints: PricePoint,
    val prizeCategories: List<PrizeCategory>,
    val wagerStatistics: WagerStatistic,
)