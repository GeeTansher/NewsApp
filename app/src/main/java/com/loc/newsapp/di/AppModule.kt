package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecases.news_remote.GetNews
import com.loc.newsapp.domain.usecases.news_remote.NewsUseCases
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager{
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository{
        return NewsRepositoryImpl(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository)
        )
    }
}