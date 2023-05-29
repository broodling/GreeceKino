package com.android.greecekino.screens.gameDetails.talon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.greecekino.R

class TalonRecyclerAdapter(
    private val dataSet: Array<Int>,
    private val onListChangeListener: OnListChangeListener
) :
    RecyclerView.Adapter<TalonRecyclerAdapter.ViewHolder>() {

    private val selectedNumbers: MutableList<Int> = mutableListOf()
    private var selectedItemPosition: Int = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.text_number)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_talon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val number = dataSet[position]
            textView.text = number.toString()
            itemView.setOnClickListener {
                selectedItemPosition = adapterPosition
                addSelectedItem(number)
                notifyItemChanged(position)
            }

            if (selectedNumbers.contains(number))
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.purple_700
                    )
                )
            else
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.purple_500
                    )
                )
        }
    }

    override fun getItemCount() = dataSet.size

    private fun addSelectedItem(selectedItem: Int) {
        when {
            selectedNumbers.contains(selectedItem) -> selectedNumbers.remove(selectedItem)

            selectedNumbers.size >= 8 -> onListChangeListener.onNumbersLimitReached()
            else -> selectedNumbers.add(selectedItem)
        }
        onListChangeListener.onNumbersSelected(selectedNumbers.size)
    }

    interface OnListChangeListener {
        fun onNumbersSelected(numbersCount: Int)
        fun onNumbersLimitReached()
    }
}
