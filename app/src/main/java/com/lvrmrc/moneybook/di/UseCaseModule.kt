package com.lvrmrc.moneybook.di

import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.usecase.GetCategoryTransactionsByPeriod
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriod
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriodAndCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    /**
     * Provides GetTransactionsByPeriod use case as singleton
     *
     * @param transactionRepo Transaction repository
     */
    @Singleton
    @Provides
    fun provideGetTransactionsByPeriod(
        transactionRepo: TransactionRepositoryImpl
    ): GetTransactionsByPeriod {
        return GetTransactionsByPeriod(transactionRepo)
    }

    /**
     * Provides GetTransactionsByPeriodAndCategory use case as singleton
     *
     * @param transactionRepo Transaction repository
     */
    @Singleton
    @Provides
    fun provideGetTransactionsByPeriodAndCategory(
        transactionRepo: TransactionRepositoryImpl
    ): GetTransactionsByPeriodAndCategory {
        return GetTransactionsByPeriodAndCategory(transactionRepo)
    }

    /**
     * Provides GetCategoryTransactionsByPeriod use case as singleton
     *
     * @param transactionRepo Transaction repository
     * @param categoryRepo Category repository
     */
    @Singleton
    @Provides
    fun provideGetCategoryTransactionsByPeriod(
        transactionRepo: TransactionRepositoryImpl, categoryRepo: CategoryRepositoryImpl
    ): GetCategoryTransactionsByPeriod {
        return GetCategoryTransactionsByPeriod(transactionRepo, categoryRepo)
    }
}