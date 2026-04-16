package com.example.msd_hotelreservation.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.search_auto_complete_feature.domain.use_case.ClearOldCacheUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect

@HiltWorker
class CleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val clearOldCacheUseCase: ClearOldCacheUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        clearOldCacheUseCase(Unit).collect()
        return Result.success()
    }
}