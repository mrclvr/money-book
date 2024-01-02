package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.outlinedIcons
import com.lvrmrc.moneybook.domain.model.IconLabel

/**
 * Single icon clickable item
 */
@Composable
fun IconGridItem(icon: ImageVector, color: Color? = null, selected: Boolean = true, onClick: (Boolean) -> Unit = {}) {
    OutlinedIconButton(modifier = Modifier
        .fillMaxSize()
        .aspectRatio(1f)
        .shadow(if (selected) 4.dp else 0.dp, RoundedCornerShape(8.dp))
        .background(colorScheme.background), shape = RoundedCornerShape(8.dp), border = BorderStroke(
        1.dp, if (selected) colorScheme.onSurfaceVariant else Color.Transparent
    ), onClick = { onClick(!selected) }

    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(shape = CircleShape)
                .background(if (selected && color != null) color else colorScheme.surfaceVariant), contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = icon,
                contentDescription = "Icon",
                tint = if (selected && color != null) colorScheme.background else colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IconGridItemPreview() {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp), contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        ) {
            IconGridItem(
                icon = outlinedIcons[IconLabel.ReceiptLong]!!,
            )
        }
    }
}