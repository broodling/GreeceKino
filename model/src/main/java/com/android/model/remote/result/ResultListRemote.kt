package com.android.model.remote.result

import com.google.gson.annotations.SerializedName

data class ResultListRemote(
    @SerializedName("content")
    val content: List<ResultRemote>?
)