package com.octocavern.data.di

import com.google.gson.Gson
import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.remote.TaigaApi
import com.octocavern.data.remote.interceptor.AuthInterceptor
import com.octocavern.data.remote.interceptor.TokenCacheInterceptor
import com.octocavern.data.remote.interceptor.TokenRefreshInterceptor
import com.octocavern.data.util.URL.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
    @Auth
    fun provideAuthInterceptor(prefs: ShishkaPrefs): Interceptor {
        return AuthInterceptor(prefs)
    }

    @Provides
    @Singleton
    @CacheToken
    fun provideTokenCacheInterceptor(prefs: ShishkaPrefs): Interceptor {
        return TokenCacheInterceptor(prefs)
    }

    @Provides
    @Singleton
    @RefreshToken
    fun provideRefreshTokenInterceptor(
        prefs: ShishkaPrefs,
        gson: Gson
    ): Interceptor {
        return TokenRefreshInterceptor(prefs, gson)
    }

    @Provides
    @Singleton
    @Logging
    fun providesLoggingInterceptor(): Interceptor {
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
        @CacheToken cacheToken: Interceptor,
        @RefreshToken refreshToken: Interceptor,
        @Auth authInterceptor: Interceptor,
        @Logging logging: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(cacheToken)
            .addInterceptor(refreshToken)
            .addInterceptor(authInterceptor)
            .build()
    }
}