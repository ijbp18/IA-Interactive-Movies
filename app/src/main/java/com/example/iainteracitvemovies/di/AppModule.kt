package com.example.iainteracitvemovies.di

import android.content.Context
import com.example.iainteracitvemovies.App
import com.example.iainteracitvemovies.BuildConfig
import com.example.iainteracitvemovies.data.network.APIService
import com.example.iainteracitvemovies.data.network.APIServiceAWS
import com.example.iainteracitvemovies.data.network.Endpoints.Companion.URL_BASE
import com.example.iainteracitvemovies.data.network.Endpoints.Companion.URL_BASE_AWS
import com.example.iainteracitvemovies.utils.DefaultDispatcherProvider
import com.example.iainteracitvemovies.utils.DispatcherProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpJsonAWS

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitConfigAWS

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorAWS


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(URL_BASE).client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("api_key", BuildConfig.API_KEY)
            it.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }

    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideApiAWS(@RetrofitConfigAWS retrofit: Retrofit): APIServiceAWS {
        return retrofit.create(APIServiceAWS::class.java)
    }


    @OkHttpJsonAWS
    @Provides
    fun provideOkHttpClientAWS(
         @InterceptorAWS headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }

    @RetrofitConfigAWS
    @Provides
    fun provideRetrofitAWS(@OkHttpJsonAWS client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(URL_BASE_AWS).client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @InterceptorAWS
    @Provides
    fun provideHeaderInterceptorAWS(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("api_key", BuildConfig.API_KEY_AWS)
            it.proceed(requestBuilder.build())
        }
    }


    private const val READ_TIMEOUT = 30
    private const val WRITE_TIMEOUT = 30
    private const val CONNECTION_TIMEOUT = 10
    private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

}