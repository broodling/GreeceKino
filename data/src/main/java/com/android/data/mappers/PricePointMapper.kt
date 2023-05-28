package com.android.data.mappers

import com.android.model.local.PricePoint
import com.android.model.remote.PricePointRemote

class PricePointMapper : BaseMapper<PricePointRemote?, PricePoint> {

    override fun map(input: PricePointRemote?) =
        PricePoint(
            addOn = input?.addOn ?: emptyList(),
            amount = input?.amount ?: Double.MAX_VALUE
        )
}
