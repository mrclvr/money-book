package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.outlinedIcons
import com.lvrmrc.moneybook.domain.model.getIconLabel
import com.lvrmrc.moneybook.presentation.ui.compose.components.IconsGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader

@Composable
fun IconsLibraryScreen(
) {
    val navController = LocalNavController.current
    var selected by remember { mutableStateOf<ImageVector?>(null) }

    Column {
        ScreenHeader(stringResource(R.string.icons))
        Column(Modifier.padding(0.dp, 15.dp)) {
            IconsGrid(outlinedIcons.toList(), selected = selected, onSelected = {
                val previousEntry = navController.previousBackStackEntry
                if (previousEntry?.destination?.route?.startsWith(Screen.Category.route) == true) {
                    selected = it
                    previousEntry.savedStateHandle["iconLabel"] = getIconLabel(outlinedIcons, it)
                    navController.popBackStack()
                }
            })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IconsLibraryScreenPreview(
) {
    NavProvider {
        IconsLibraryScreen()
    }
}