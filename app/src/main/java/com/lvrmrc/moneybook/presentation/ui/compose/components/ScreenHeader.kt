package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.presentation.ui.theme.primary400


@Composable
fun ScreenHeader(
    title: String, color: Color
) {
    Row(
        Modifier
            .clip(RoundedCornerShape(0, 0, 70, 70))
            .background(color)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = title,
            textAlign = TextAlign.Center,
            color = colorScheme.background,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenHeaderPreview(
) {
    ScreenHeader("Title", primary400)
}