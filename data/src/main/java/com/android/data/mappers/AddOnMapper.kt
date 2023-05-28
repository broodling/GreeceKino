package com.android.data.mappers

import com.android.model.local.AddOn
import com.android.model.remote.AddOnRemote

class AddOnMapper : BaseMapper<List<AddOnRemote>?, List<AddOn>> {

    override fun map(input: List<AddOnRemote>?): List<AddOn> {
        val addOnList: MutableList<AddOn> = mutableListOf()
        input?.let {

            for (addOn in it) {
                addOnList.add(
                    AddOn(
                        amount = addOn.amount ?: Double.MAX_VALUE,
                        gameType = addOn.gameType ?: ""
                    )
                )
            }
        }
        return addOnList
    }
}