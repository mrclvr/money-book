package com.lvrmrc.moneybook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.di.TransactionCallback
import com.lvrmrc.moneybook.utils.Converters
import javax.inject.Provider

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, provider: Provider<TransactionDao>): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context, provider).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context, provider: Provider<TransactionDao>): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "app_database"
            ).addCallback(TransactionCallback(provider)).build()
        }
    }
}