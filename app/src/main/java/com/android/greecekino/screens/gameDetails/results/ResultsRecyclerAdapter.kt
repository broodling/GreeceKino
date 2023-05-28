package com.android.greecekino.screens.gameDetails.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.greecekino.R
import com.android.greecekino.ui.getFormattedTime
import com.android.model.local.result.Result

class ResultsRecyclerAdapter(private val resultList: List<Result>) :
    RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDrawInfo: TextView
        val recyclerDraftNumbers: RecyclerView

        init {
            textDrawInfo = view.findViewById(R.id.text_draw_info)
            recyclerDraftNumbers = view.findViewById(R.id.recycler_drafted_number)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result_draw, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = resultList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            textDrawInfo.text = itemView.context.getString(
                R.string.game_details_placeholder,
                getFormattedTime(resultList[position].drawTime, "dd:MM hh:mm"),
                resultList[position].visualDraw.toString()
            )
            recyclerDraftNumbers.layoutManager = GridLayoutManager(itemView.context, 5)
            recyclerDraftNumbers.setHasFixedSize(true)
            recyclerDraftNumbers.adapter =
                NumbersRecyclerAdapter(resultList[position].winningNumbers.list)
        }
    }
}