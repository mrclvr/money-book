package com.lvrmrc.moneybook.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lvrmrc.moneybook.data.source.db.converter.Converters
import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import javax.inject.Provider

@Database(entities = [CategoryEntity::class, TransactionEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, catProvider: Provider<CategoryDao>, transProvider: Provider<TransactionDao>): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, catProvider, transProvider).also { INSTANCE = it }
            }

        private fun buildDatabase(
            context: Context, catProvider: Provider<CategoryDao>, transProvider: Provider<TransactionDao>
        ): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "app_database"
            ).addCallback(DatabaseCallback(catProvider, transProvider)).build()
        }
    }
}