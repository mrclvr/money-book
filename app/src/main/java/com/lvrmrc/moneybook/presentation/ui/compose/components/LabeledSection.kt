package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.FABLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun LabeledSection(
    fillHeight: Boolean = false,
    sectionTitle: String,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    content: @Composable () -> Unit = {}
) {

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.primary)
                .padding(15.dp)
        ) {
            Text(sectionTitle, color = colorScheme.onPrimary)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.primaryContainer)
                .padding(15.dp)
                .weight(1f, fillHeight),
            horizontalArrangement,
            verticalAlignment = Alignment.Top
        ) { content() }

    }
}

@Preview
@Composable
fun LabeledSectionPreview(
) {
    MoneyBookTheme {
        FABLayout {
            Column {
                LabeledSection(sectionTitle = "Section 1", content = {
                    Icon(Icons.Rounded.Home, "Test")
                })
                LabeledSection(sectionTitle = "Section 2", content = {
                    Icon(Icons.Rounded.Home, "Test")
                })
                LabeledSection(fillHeight = true, sectionTitle = "Section 3", content = {
                    Icon(Icons.Rounded.Home, "Test")
                })
            }
        }
    }
}