package com.example.search_auto_complete_feature.data.local.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import com.example.search_auto_complete_feature.data.local.entity.SearchSuggestionEntity

@Database(entities = [SearchSuggestionEntity::class], version = 1, exportSchema = false)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}