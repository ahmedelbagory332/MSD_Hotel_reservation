package com.example.search_auto_complete_feature.domain.use_case

import com.example.core.base.domain.BaseUseCase
import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import javax.inject.Inject

class ClearOldCacheUseCase @Inject constructor(
    private val dao: SearchDao
) : BaseUseCase<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        val oneWeekAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
        dao.clearOldCache(oneWeekAgo)
    }
}