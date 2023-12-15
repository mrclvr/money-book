package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.Category

@Composable
fun CategoryItem(category: Category, selected: Boolean = true, onClick: (Boolean) -> Unit = {}) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(if (selected) category.color else Color.Transparent)
            .clickable { onClick(!selected) }, contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(shape = CircleShape)
                .background(category.color), contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = "${category.label} category",
                tint = if (category.lightText) colorScheme.background else colorScheme.onBackground
            )
        }
    }

}

@Composable
@Preview
fun CategoryItemPreview() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        CategoryItem(mockCategories[0])
    }
}