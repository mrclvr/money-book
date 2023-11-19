package com.lvrmrc.moneybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lvrmrc.moneybook.ui.core.components.AppLayout
import com.lvrmrc.moneybook.ui.core.theme.MoneyBookTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoneyBookTheme {
                AppLayout()
            }
        }
    }
}