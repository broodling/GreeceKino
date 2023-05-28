package com.android.model.remote

import com.google.gson.annotations.SerializedName

data class PricePointRemote(
    @SerializedName("addOn") val addOn: List<AddOnRemote>?,
    @SerializedName("amount") val amount: Double?,
)