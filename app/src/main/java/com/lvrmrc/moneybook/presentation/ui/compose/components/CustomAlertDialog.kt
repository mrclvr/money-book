package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    title: String,
    text: String,
    icon: ImageVector? = null,
) {
    AlertDialog(icon = {
        icon?.let { Icon(icon, contentDescription = "Icon") }
    }, title = {
        Text(text = title)
    }, text = {
        Text(text = text, textAlign = TextAlign.Center)
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text("Confirm")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("Dismiss")
        }
    }, properties = DialogProperties(dismissOnClickOutside = false))
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    AppLayout {
        CustomAlertDialog(title = "Title", text = "Text")
    }
}