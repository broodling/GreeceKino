package com.android.model.remote

import com.google.gson.annotations.SerializedName

data class PrizeCategoryRemote(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("divident")
    val divident:Double?,
    @SerializedName("winners")
    val winners:Int?,
    @SerializedName("distributed")
    val distributed:Double?,
    @SerializedName("jackpot")
    val jackpot:Double?,
    @SerializedName("fixed")
    val fixed:Double?,
    @SerializedName("categoryType")
    val categoryType:Int?,
    @SerializedName("gameType")
    val gameType:String?,
)
