package com.lvrmrc.moneybook.utils

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getFormattedNowTime(): String {
    val nowDateTime = Calendar.getInstance().time
    val formatter = SimpleDateFormat("MMM d, yyyy, hh:mm a", Locale.getDefault())
    return formatter.format(nowDateTime)

}

fun dateFromTimestamp(timestamp: Long): String {
    println(Date(timestamp).toString())
    return Date(timestamp).toString()

}
