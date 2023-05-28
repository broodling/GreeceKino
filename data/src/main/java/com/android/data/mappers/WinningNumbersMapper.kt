package com.android.data.mappers

import com.android.model.local.result.WinningNumbers
import com.android.model.remote.result.WinningNumbersRemote

class WinningNumbersMapper : BaseMapper<WinningNumbersRemote?, WinningNumbers> {

    override fun map(input: WinningNumbersRemote?): WinningNumbers {
        val winingNumbersList: MutableList<Int> = mutableListOf()
        input?.list?.let {
            winingNumbersList.addAll(it)
        }
        return WinningNumbers(winingNumbersList)
    }
}
