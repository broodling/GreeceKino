package com.android.data.mappers

import com.android.model.local.WagerStatistic
import com.android.model.remote.WagerStatisticsRemote

class WagerStatisticMapper(private val addOnMapper: AddOnMapper) :
    BaseMapper<WagerStatisticsRemote?, WagerStatistic> {

    override fun map(input: WagerStatisticsRemote?) =
        WagerStatistic(
            columns = input?.columns ?: Int.MAX_VALUE,
            wagers = input?.wagers ?: Int.MAX_VALUE,
            addOn = addOnMapper.map(input?.addOn)
        )
}
