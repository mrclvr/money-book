package com.lvrmrc.moneybook.di

import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.usecase.GetCategoryTransactionsByPeriod
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriodAndCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCategoryTransactionsByPeriod(
        transactionRepo: TransactionRepositoryImpl
    ): GetCategoryTransactionsByPeriod {
        return GetCategoryTransactionsByPeriod(transactionRepo)
    }

    @Singleton
    @Provides
    fun provideGetTransactionsByPeriodAndCategory(
        transactionRepo: TransactionRepositoryImpl
    ): GetTransactionsByPeriodAndCategory {
        return GetTransactionsByPeriodAndCategory(transactionRepo)
    }
}