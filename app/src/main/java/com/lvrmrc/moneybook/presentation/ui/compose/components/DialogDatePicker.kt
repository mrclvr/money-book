package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar


@Composable
fun DialogDatePicker(onDateSelected: (Long) -> Unit = {}) {

    var datePickerOpen by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center) {
        IconButton(modifier = Modifier.size(56.dp), colors = IconButtonDefaults.filledIconButtonColors(), onClick = {
            datePickerOpen = true
        }) {
            Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = "Open date picker")
        }
    }

    if (datePickerOpen) {
        DialogDatePicker(onDateSelected = { onDateSelected(it) }, onClose = { datePickerOpen = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogDatePicker(
    onClose: () -> Unit, onDateSelected: (Long) -> Unit
) {
    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    val selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()

    DatePickerDialog(onDismissRequest = { onClose() }, confirmButton = {
        TextButton(onClick = {
            onDateSelected(selectedDate)
            onClose()
        }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = onClose) {
            Text("CANCEL")
        }
    }) {
        DatePicker(
            state = datePickerState
        )
    }
}

//private fun timestampToDate(timestamp: Long): String {
//    val formatter = SimpleDateFormat("yyyyMMdd")
//    return formatter.format(Date(timestamp))
//}

@Preview
@Composable

fun DialogDatePickerPreview() {
    DialogDatePicker()
}