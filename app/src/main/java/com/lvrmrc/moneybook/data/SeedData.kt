package com.lvrmrc.moneybook.data

import com.lvrmrc.moneybook.data.entity.Transaction
import java.time.LocalDate

val defaultTransactions = arrayOf(
    Transaction(
        amount = 1.0, title = "Food 1", type = "Expense", date = LocalDate.of(2023, 2, 14)
    ),
    Transaction(
        amount = 2.0, title = "Health 2", type = "Expense", date = LocalDate.of(2023, 8, 25)
    ),
    Transaction(
        amount = 3.0, title = "Cinema 3", type = "Expense", date = LocalDate.of(2023, 11, 22)
    ),
    Transaction(
        amount = 4.0, title = "Food 4", type = "Expense", date = LocalDate.of(2023, 11, 23)
    ),
    Transaction(
        amount = 5.0, title = "Health 5", type = "Expense", date = LocalDate.of(2023, 11, 24)
    ),
    Transaction(
        amount = 6.0, title = "Cinema 6", type = "Expense", date = LocalDate.of(2023, 11, 24)
    ),
)