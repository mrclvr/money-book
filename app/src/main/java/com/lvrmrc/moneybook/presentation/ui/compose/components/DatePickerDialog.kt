package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.utils.toLocalDateTime
import com.lvrmrc.moneybook.utils.toMillis
import java.time.LocalDateTime

/**
 * Date picker dialog
 */
@Composable
fun DatePickerDialog(date: LocalDateTime = LocalDateTime.now(), onDateSelected: (LocalDateTime) -> Unit = {}) {

    var datePickerOpen by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center) {
        IconButton(modifier = Modifier.size(56.dp), colors = IconButtonDefaults.filledIconButtonColors(), onClick = {
            datePickerOpen = true
        }) {
            Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = stringResource(R.string.open_date_picker))
        }
    }

    if (datePickerOpen) {
        DatePickerDialog(date = date, onDateSelected = { onDateSelected(it) }, onClose = { datePickerOpen = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDialog(
    date: LocalDateTime = LocalDateTime.now(), onClose: () -> Unit, onDateSelected: (LocalDateTime) -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date.toMillis())
    val selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()

    DatePickerDialog(onDismissRequest = { onClose() }, confirmButton = {
        TextButton(onClick = {
            onDateSelected(selectedDate.toLocalDateTime())
            onClose()
        }) {
            Text(stringResource(R.string.confirm))
        }
    }, dismissButton = {
        TextButton(onClick = onClose) {
            Text(stringResource(R.string.dismiss))
        }
    }) {
        DatePicker(
            state = datePickerState
        )
    }
}

@Preview
@Composable

fun DatePickerDialogPreview() {
    DatePickerDialog()
}