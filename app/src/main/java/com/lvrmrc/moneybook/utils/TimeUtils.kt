package com.lvrmrc.moneybook.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getFormattedNowTime(): String {
    val nowDateTime = Calendar.getInstance().time
    val formatter = SimpleDateFormat("MMM d, yyyy, hh:mm a", Locale.getDefault())
    return formatter.format(nowDateTime)

}
