package com.example.search_auto_complete_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_suggestions")
data class SearchSuggestionEntity(
    @PrimaryKey val query: String,
    val suggestionsJson: String,
    val lastUsedTimestamp: Long = System.currentTimeMillis()
)