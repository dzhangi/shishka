package com.octocavern.data.di

import android.content.Context
import com.octocavern.data.local.ShishkaPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): ShishkaPrefs {
        return ShishkaPrefs(context)
    }
}