package com.example.search_auto_complete_feature.di

import android.content.Context
import androidx.room.Room
import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import com.example.search_auto_complete_feature.data.local.data_base.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SearchDatabase {
        return Room.databaseBuilder(context, SearchDatabase::class.java, "search_db").build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(db: SearchDatabase): SearchDao = db.searchDao()
}