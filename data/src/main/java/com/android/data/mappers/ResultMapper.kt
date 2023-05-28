package com.android.data.mappers

import com.android.model.local.result.Result
import com.android.model.remote.result.ResultRemote

class ResultMapper(
    private val pricePointMapper: PricePointMapper,
    private val prizeCategoryMapper: PrizeCategoryMapper,
    private val wagerStatisticMapper: WagerStatisticMapper,
    private val winningNumbersMapper: WinningNumbersMapper
) : BaseMapper<ResultRemote, Result> {

    override fun map(input: ResultRemote) = Result(
        gameId = input.gameId ?: Int.MAX_VALUE,
        drawId = input.drawId ?: Int.MAX_VALUE,
        drawTime = input.drawTime ?: Long.MAX_VALUE,
        status = input.status ?: false,
        drawBreak = input.drawBreak ?: Int.MAX_VALUE,
        visualDraw = input.visualDraw ?: Long.MAX_VALUE,
        pricePoints = pricePointMapper.map(input.pricePoints),
        prizeCategories = prizeCategoryMapper.map(input.prizeCategories),
        wagerStatistics = wagerStatisticMapper.map(input.wagerStatistics),
        winningNumbers = winningNumbersMapper.map(input.winningNumbers)
    )

}