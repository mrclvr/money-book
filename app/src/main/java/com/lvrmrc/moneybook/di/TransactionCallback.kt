package com.lvrmrc.moneybook.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.entity.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Provider


class TransactionCallback(
    private val transactionProvider: Provider<TransactionDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        seed()
    }

    private fun seed() {
        val transactions = arrayOf(
            Transaction(
                amount = 5.55, title = "Food", type = "Expense", date = LocalDate.of(2018, 2, 14)
            ), Transaction(
                amount = 5.55, title = "Health", type = "Expense", date = LocalDate.of(1991, 8, 25)
            ), Transaction(
                amount = 5.55, title = "Cinema", type = "Expense", date = LocalDate.of(2023, 11, 24)
            )
        )
        applicationScope.launch(Dispatchers.IO) {
            transactionProvider.get().insert(*transactions)
        }
    }
}