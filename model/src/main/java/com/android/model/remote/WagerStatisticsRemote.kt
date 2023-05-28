package com.android.model.remote

import com.google.gson.annotations.SerializedName

data class WagerStatisticsRemote(
    @SerializedName("columns")
    val columns: Int?,
    @SerializedName("wagers")
    val wagers: Int?,
    @SerializedName("addOn") val addOn: List<AddOnRemote>?
)
