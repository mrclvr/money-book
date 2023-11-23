package com.lvrmrc.moneybook.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.entity.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Provider


class TransactionCallback(
    private val provider: Provider<TransactionDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            seed()
        }
    }

    private fun seed() {
        val transactions = arrayOf(
            Transaction(
                id = 123, amount = 5.55, title = "Food", date = LocalDateTime.now(), type = "Expense"
            ), Transaction(
                id = 345, amount = 5.55, title = "Health", date = LocalDateTime.now(), type = "Expense"
            ), Transaction(
                id = 999, amount = 5.55, title = "Cinema", date = LocalDateTime.now(), type = "Expense"
            )
        )
        provider.get().insert(*transactions)
    }
}