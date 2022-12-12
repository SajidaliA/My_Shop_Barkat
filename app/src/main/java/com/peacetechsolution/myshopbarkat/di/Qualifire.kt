package com.peacetechsolution.snowb.di

import javax.inject.Qualifier

/**
 * Created by Sajid.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

