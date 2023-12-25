package com.lvrmrc.moneybook.presentation.ui.compose.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.FABLayout

/**
 * Full width section with title
 */
@Composable
fun LabeledSection(
    modifier: Modifier = Modifier,
    fillHeight: Boolean = false,
    sectionTitle: String = "",
    titleColor: Color = colorScheme.primary,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    topRightContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {

    Column(modifier) {
        if (sectionTitle.isNotBlank()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(sectionTitle, color = titleColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                topRightContent()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), horizontalArrangement, verticalAlignment = Alignment.Top
        ) { content() }

    }
}

@Preview
@Composable
fun LabeledSectionPreview(
) {
    AppLayout {
        FABLayout {
            Column() {
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