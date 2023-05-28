package com.android.model.remote

import com.google.gson.annotations.SerializedName

data class AddOnRemote(
    @SerializedName("amount")
    val amount:Double?,
    @SerializedName("gameType")
    val gameType:String?)
