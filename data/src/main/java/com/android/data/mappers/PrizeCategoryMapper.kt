package com.android.data.mappers

import com.android.model.local.PrizeCategory
import com.android.model.remote.PrizeCategoryRemote

class PrizeCategoryMapper : BaseMapper<List<PrizeCategoryRemote>?, List<PrizeCategory>> {

    override fun map(input: List<PrizeCategoryRemote>?): List<PrizeCategory> {
        val prizeCategories: MutableList<PrizeCategory> = mutableListOf()
        input?.let { prizeCategoriesRemote ->
            for (prizeCategory in prizeCategoriesRemote) {
                prizeCategories.add(
                    PrizeCategory(
                        id = prizeCategory.id ?: Int.MAX_VALUE,
                        divident = prizeCategory.divident ?: Double.MAX_VALUE,
                        winners = prizeCategory.winners ?: Int.MAX_VALUE,
                        distributed = prizeCategory.distributed ?: Double.MAX_VALUE,
                        jackpot = prizeCategory.jackpot ?: Double.MAX_VALUE,
                        fixed = prizeCategory.fixed ?: Double.MAX_VALUE,
                        categoryType = prizeCategory.categoryType ?: Int.MAX_VALUE,
                        gameType = prizeCategory.gameType ?: ""
                    )
                )
            }
        }
        return prizeCategories
    }
}