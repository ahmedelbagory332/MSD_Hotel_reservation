package com.example.search_auto_complete_feature.di

import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import com.example.search_auto_complete_feature.data.network.api.SearchApi
import com.example.search_auto_complete_feature.data.repository.SearchRepositoryImpl
import com.example.search_auto_complete_feature.domain.repository.SearchRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi,
        dao: SearchDao,
        gson: Gson
    ): SearchRepository = SearchRepositoryImpl(
        api = api,
        dao = dao,
        gson = gson
    )
}