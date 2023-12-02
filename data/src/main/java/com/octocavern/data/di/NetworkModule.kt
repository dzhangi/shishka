package com.octocavern.data.di

import com.google.gson.Gson
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.remote.TaigaApi
import com.octocavern.data.remote.interceptor.TokenCacheInterceptor
import com.octocavern.data.remote.interceptor.TokenRefreshInterceptor
import com.octocavern.data.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TaigaApi = retrofit.create(TaigaApi::class.java)

    @Provides
    @Singleton
    fun provideTokenCacheInterceptor(prefs: ShishkaPrefs): TokenCacheInterceptor {
        return TokenCacheInterceptor(prefs)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenInterceptor(
        prefs: ShishkaPrefs,
        gson: Gson
    ): TokenRefreshInterceptor {
        return TokenRefreshInterceptor(prefs, gson      )
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenCacheInterceptor: TokenCacheInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenRefreshInterceptor: TokenRefreshInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenRefreshInterceptor)
            .addInterceptor(tokenCacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}