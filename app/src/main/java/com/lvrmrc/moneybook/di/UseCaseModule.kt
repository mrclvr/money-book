package com.lvrmrc.moneybook.di

import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriod
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
    fun provideGetTransactionsByPeriod(
        transactionRepo: TransactionRepositoryImpl, appState: AppState
    ): GetTransactionsByPeriod {
        return GetTransactionsByPeriod(transactionRepo, appState)
    }
}