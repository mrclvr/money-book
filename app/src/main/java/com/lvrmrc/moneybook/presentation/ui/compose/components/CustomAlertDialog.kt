package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout

/**
 * Alert dialog
 */
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
            Text(stringResource(R.string.confirm))
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text(stringResource(R.string.dismiss))
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