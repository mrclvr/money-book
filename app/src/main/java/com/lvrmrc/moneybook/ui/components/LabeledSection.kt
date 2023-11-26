package com.lvrmrc.moneybook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledSection(
    sectionTitle: String, horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween, content: @Composable () -> Unit = {}
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row {
            Text(sectionTitle, color = MaterialTheme.colorScheme.primaryContainer)
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement, verticalAlignment = Alignment.CenterVertically
        ) { content() }

    }
}