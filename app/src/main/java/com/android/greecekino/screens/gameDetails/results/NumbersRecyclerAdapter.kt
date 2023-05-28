package com.android.greecekino.screens.gameDetails.results

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greecekino.R
import java.util.Random


class NumbersRecyclerAdapter(private val numbers: List<Int>) :
    RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDraftNumber: TextView

        init {
            textDraftNumber = view.findViewById(R.id.text_ball_number)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_draft_ball, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = numbers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            textDraftNumber.text = numbers[position].toString()
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            val drawable = textDraftNumber.background as GradientDrawable
            drawable.mutate()
            drawable.setStroke(3, color)
        }
    }
}