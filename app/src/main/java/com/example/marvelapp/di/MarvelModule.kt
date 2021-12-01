package com.example.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.example.marvelapp.data.source.local.MarvelDao
import com.example.marvelapp.data.source.local.MarvelDatabase
import com.example.marvelapp.data.source.remote.MarvelRemoteDataSource
import com.example.marvelapp.data.source.remote.MarvelService
import com.example.marvelapp.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(okHttpClient: OkHttpClient): MarvelService {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build().create(MarvelService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideMarvelDao(@ApplicationContext context: Context): MarvelDao {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "marvel.db"
        ).build().marvelDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object MarvelRepository {

    @Provides
    fun provideMarvelRepository(marvelDao: MarvelDao, marvelService: MarvelService): MarvelRemoteDataSource {
        return MarvelRepository(marvelDao, marvelService)
    }
}