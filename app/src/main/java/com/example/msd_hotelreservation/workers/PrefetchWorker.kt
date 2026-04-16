package com.example.msd_hotelreservation.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.search_auto_complete_feature.data.network.api.SearchApi
import com.example.search_auto_complete_feature.domain.repository.SearchRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PrefetchWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: SearchRepository,
    private val api: SearchApi
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        if (runAttemptCount > 3) {
            return Result.failure()
        }
        return try {
            val trends = api.getTrendingQueries()
            Log.d("PrefetchWorker", "doWork: $trends ")
            trends.forEach { repository.fetchSuggestions(it) }
            Result.success()
        } catch (e: Exception) { Result.retry() }
    }
}