package com.tpb.pagesaver.dagger.module

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tpb.pagesaver.BuildConfig
import com.tpb.pagesaver.data.network.MercuryService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by theo on 29/08/17.
 */
@Module
class MercuryModule(val context: Context, val baseURL: String) {


    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        return Cache(application.cacheDir, (10 * 1024 * 1024).toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .setLenient()
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder()
                            .header("x-api-key", BuildConfig.MERCURY_API_KEY)
                            .build())
                }
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(okHttpClient)
                .baseUrl(baseURL)
                .build()
    }

    @Provides
    @Singleton
    fun provideMercuryService(retrofit: Retrofit): MercuryService {
        return retrofit.create(MercuryService::class.java)
    }

}