package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.outlinedIcons
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.IconStyle
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.getIconByName
import com.lvrmrc.moneybook.domain.model.getIconLabel
import com.lvrmrc.moneybook.presentation.ui.compose.components.ColorsRow
import com.lvrmrc.moneybook.presentation.ui.compose.components.CustomAlertDialog
import com.lvrmrc.moneybook.presentation.ui.compose.components.IconsGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionTypeRadio
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.FABLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    iconLabel: IconLabel?, vm: CategoryViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    LaunchedEffect(key1 = iconLabel) {
        iconLabel?.let {
            getIconByName(iconLabel, IconStyle.Outlined)?.let { icon ->
                vm.setIcon(icon)
            }
        }
    }

    CategoryScreen(label = vm.label,
        icon = vm.icon,
        color = vm.color,
        type = vm.type,
        isUpdate = vm.categoryId != null,
        fabEnabled = vm.fabEnabled.value,
        onSetLabel = { vm.setLabel(it) },
        onSetIcon = { vm.setIcon(it) },
        onSetColor = { vm.setColor(it) },
        onSetType = { vm.setType(it) },
        onUpdate = {
            vm.addCategory()
            navController.popBackStack()
        },
        onDelete = {
            vm.deleteCategory()
            navController.popBackStack()
        })
}

@Composable

private fun CategoryScreen(
    label: String = "",
    icon: ImageVector? = null,
    color: Color? = null,
    type: TransactionType? = null,
    isUpdate: Boolean = false,
    fabEnabled: Boolean = false,
    onSetLabel: (String) -> Unit = {},
    onSetIcon: (ImageVector) -> Unit = {},
    onSetColor: (Color) -> Unit = {},
    onSetType: (TransactionType) -> Unit = {},
    onUpdate: () -> Unit = {},
    onDelete: () -> Unit = {}
) {

    val fabText = if (isUpdate) R.string.update else R.string.add_category
    val headerText = if (isUpdate) R.string.update_category else R.string.add_new_category
    val iconsList = outlinedIcons.toList().slice(1..7).toMutableList()

    /**
     * Replace first icon with selected if not visible
     */
    icon?.let {
        if (iconsList.find { it.second == icon } == null) {
            val iconLabel = getIconLabel(outlinedIcons, icon)
            iconsList[0] = Pair(iconLabel, icon)
        }
    }


    FABLayout(topBar = {
        ScreenHeader(
            title = stringResource(
                headerText
            ), color = colorScheme.primary
        )
    }, fabText = stringResource(fabText), fabEnabled = fabEnabled, onFabAction = { onUpdate() }) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly
        ) {

            // Label
            LabeledSection(
                sectionTitle = stringResource(R.string.label),
                topRightContent = {
                    if (isUpdate) {
                        var showDialog by remember { mutableStateOf(false) }

                        // Delete icon
                        Icon(
                            Icons.Outlined.Delete,
                            stringResource(R.string.delete_category),
                            Modifier.clickable { showDialog = true },
                            colorScheme.error
                        )

                        // Deletion dialog
                        if (showDialog) {
                            CustomAlertDialog(title = stringResource(R.string.confirm_deletion),
                                text = stringResource(R.string.category_delete_msg, label),
                                onDismissRequest = { showDialog = false },
                                onConfirmation = {
                                    showDialog = false
                                    onDelete()
                                })
                        }
                    }
                }
            ) {
                TextField(modifier = Modifier.fillMaxWidth(1f), singleLine = true, value = label, colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colorScheme.primary,
                    unfocusedIndicatorColor = colorScheme.primary,
                ), onValueChange = {
                    if (it.length <= 30) onSetLabel(it)
                })
            }

            // Type
            LabeledSection(
                sectionTitle = stringResource(R.string.type)
            ) {
                TransactionTypeRadio(type, onSelected = { onSetType(it) })
            }

            // Icon
            LabeledSection(
                sectionTitle = stringResource(R.string.icon),
            ) {
                IconsGrid(iconsList, true, color, icon, onSelected = { onSetIcon(it) })
            }

            // Color
            LabeledSection(
                sectionTitle = stringResource(R.string.color)
            ) {
                ColorsRow(selected = color, onSelected = { onSetColor(it) })
            }

        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoryScreenPreview(
) {
    NavProvider {
        CategoryScreen(label = "New category")
    }
}