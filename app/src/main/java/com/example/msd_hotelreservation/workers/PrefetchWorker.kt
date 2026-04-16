package com.example.msd_hotelreservation.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.core.base.network.NetWorkCall
import com.example.search_auto_complete_feature.domain.use_case.FetchSuggestionsUseCase
import com.example.search_auto_complete_feature.domain.use_case.GetTrendingQueriesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.first

@HiltWorker
class PrefetchWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val fetchSuggestions: FetchSuggestionsUseCase,
    private val getTrendingQueries: GetTrendingQueriesUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        if (runAttemptCount > 3) {
            return Result.failure()
        }
        return try {
            val result = getTrendingQueries(Unit)
                .filterNot { it is NetWorkCall.Loading } // Ignore the loading state
                .first()
            if (result is NetWorkCall.Success) {
                val trends = result.data ?: emptyList()
                Log.d("bego PrefetchWorker", "doWork: $trends")
                trends.forEach {
                    // in this case we don't care about the result of the fetch it will save in DB
                    fetchSuggestions(it).collect()
                }

            }
            if (result is NetWorkCall.Error) return Result.retry()
            Result.success()
        } catch (e: Exception) {
            Log.d("bego PrefetchWorker", "doWork Exception: $e")
            Result.retry()
        }
    }
}