package com.lvrmrc.moneybook.di

import com.lvrmrc.moneybook.data.AppState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppStateModule {

    @Provides
    @Singleton
    fun provideAppState(): AppState {
        return AppState.getInstance()
    }
}