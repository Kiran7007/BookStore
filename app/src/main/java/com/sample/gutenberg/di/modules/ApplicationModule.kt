package com.sample.gutenberg.di.modules

import android.content.Context
import androidx.room.Room
import com.sample.gutenberg.data.db.DatabaseHelper
import com.sample.gutenberg.data.remote.ApiService
import com.sample.gutenberg.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideUrl() = "https://www.google.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDbHelper(@ApplicationContext context: Context): DatabaseHelper =
        Room.databaseBuilder(context, DatabaseHelper::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCategoryDao(databaseHelper: DatabaseHelper) = databaseHelper.categoryDao()

    @Singleton
    @Provides
    fun provideBookDao(databaseHelper: DatabaseHelper) = databaseHelper.bookDao()
}