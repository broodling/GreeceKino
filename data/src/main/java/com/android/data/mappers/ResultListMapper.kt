package com.android.data.mappers

import com.android.model.local.result.Result
import com.android.model.local.result.ResultList
import com.android.model.remote.result.ResultListRemote

class ResultListMapper(private val resultMapper: ResultMapper) :
    BaseMapper<ResultListRemote?, ResultList> {

    override fun map(input: ResultListRemote?): ResultList {
        val results: MutableList<Result> = mutableListOf()
        input?.content?.let { contentList ->
            for (result in contentList) {
                results.add(resultMapper.map(result))
            }
        }
        return ResultList(results)
    }
}
