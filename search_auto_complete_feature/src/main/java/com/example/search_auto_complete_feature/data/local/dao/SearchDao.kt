package com.example.search_auto_complete_feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.search_auto_complete_feature.data.local.entity.SearchSuggestionEntity

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_suggestions WHERE `query` = :query")
    suspend fun getSuggestions(query: String): SearchSuggestionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSuggestions(entity: SearchSuggestionEntity)

    // Wipe out the cash that no one has touched for a week
    @Query("DELETE FROM search_suggestions WHERE lastUsedTimestamp < :threshold")
    suspend fun clearOldCache(threshold: Long)
}