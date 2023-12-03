package com.octocavern.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CacheToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Logging