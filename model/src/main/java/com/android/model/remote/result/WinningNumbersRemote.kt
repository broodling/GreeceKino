package com.android.model.remote.result

import com.google.gson.annotations.SerializedName

data class WinningNumbersRemote(
    @SerializedName("list")
    val list: List<Int>?
)