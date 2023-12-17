package com.lvrmrc.moneybook.di

import com.lvrmrc.moneybook.data.AppState
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
        transactionRepo: TransactionRepositoryImpl, appState: AppState
    ): GetCategoryTransactionsByPeriod {
        return GetCategoryTransactionsByPeriod(transactionRepo, appState)
    }

    @Singleton
    @Provides
    fun provideGetTransactionsByPeriodAndCategory(
        transactionRepo: TransactionRepositoryImpl, appState: AppState
    ): GetTransactionsByPeriodAndCategory {
        return GetTransactionsByPeriodAndCategory(transactionRepo, appState)
    }
}