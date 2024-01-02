package com.lvrmrc.moneybook.di

import android.content.Context
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.data.source.db.AppDatabase
import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides singleton DB instance
     *
     * @param context app context
     * @param catProvider category DAO provider
     * @param transProvider transaction DAO provider
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context, catProvider: Provider<CategoryDao>, transProvider: Provider<TransactionDao>
    ): AppDatabase {
        return AppDatabase.getInstance(context, catProvider, transProvider)
    }

    /**
     * Provides Category DAO singleton instance
     *
     * @param appDatabase DB instance
     */
    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    /**
     * Provides Category repository singleton instance
     *
     * @param categoryDao Category DAO instance
     */
    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepositoryImpl {
        return CategoryRepositoryImpl(categoryDao)
    }

    /**
     * Provides Transaction DAO singleton instance
     *
     * @param appDatabase DB instance
     */
    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    /**
     * Provides Transaction repository singleton instance
     *
     * @param transactionDao Transaction DAO instance
     */
    @Provides
    @Singleton
    fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepositoryImpl {
        return TransactionRepositoryImpl(transactionDao)
    }
}