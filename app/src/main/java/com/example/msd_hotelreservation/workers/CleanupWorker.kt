package com.example.msd_hotelreservation.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dao: SearchDao
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val oneWeekAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
        dao.clearOldCache(oneWeekAgo)
        return Result.success()
    }
}