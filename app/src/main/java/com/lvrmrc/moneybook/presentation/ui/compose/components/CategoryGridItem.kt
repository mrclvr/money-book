package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.data.outlinedIcons
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.theme.primary200
import java.util.UUID

/**
 * Single category clickable item
 */
@Composable
fun CategoryGridItem(category: Category, selected: Boolean = true, onClick: (Boolean) -> Unit = {}) {

    @Composable
    fun getColor(): Color {
        return when (category.lightText) {
            true -> colorScheme.background
            else -> colorScheme.onBackground
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .aspectRatio(1f)
        .clip(shape = RoundedCornerShape(8.dp))
        .background(if (selected) category.color else Color.Transparent)
        .clickable { onClick(!selected) }
        .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(shape = CircleShape)
                .background(category.color), contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(36.dp), imageVector = category.icon, contentDescription = category.label, tint = getColor()
            )
        }
        Text(
            text = category.label,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) getColor() else colorScheme.onBackground
        )
    }
}

@Composable
@Preview
fun CategoryGridItemPreview() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        CategoryGridItem(
            Category(
                id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb28"),
                label = "Test",
                icon = outlinedIcons[IconLabel.ReceiptLong]!!,
                color = primary200,
                type = TransactionType.EXPENSE,
                lightText = false,
            )
        )
    }
}