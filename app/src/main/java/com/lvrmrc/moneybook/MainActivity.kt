package com.lvrmrc.moneybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppTabsLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoneyBookTheme {
                AppTabsLayout()
            }
        }
    }
}