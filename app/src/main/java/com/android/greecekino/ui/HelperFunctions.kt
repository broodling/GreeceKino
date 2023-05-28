package com.android.greecekino.ui

import java.text.SimpleDateFormat
import java.util.Calendar

fun getFormattedTime(milliSeconds: Long, format: String): String {
    val formatter = SimpleDateFormat(format)
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}