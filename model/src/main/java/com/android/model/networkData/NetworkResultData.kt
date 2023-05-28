package com.android.model.networkData

data class NetworkResultData<T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null,
    val apiCode: String? = null
) {
    companion object {
        fun <T> Success(data: T?): NetworkResultData<T> =
            NetworkResultData(
                status = Status.SUCCESS,
                data = data,
            )

        fun <T> Error(
            data: T? = null,
            throwable: Throwable?,
            apiCode: String? = null
        ): NetworkResultData<T> =
            NetworkResultData(
                status = Status.ERROR,
                data = data,
                throwable = throwable,
                apiCode = apiCode
            )

        fun <T> Loading(data: T? = null): NetworkResultData<T> =
            NetworkResultData(
                status = Status.LOADING,
                data = data
            )
    }
}