package com.example.msd_hotelreservation.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class WorkManagerFacade(
    private val context: Context
) {

    private val workManager = WorkManager.getInstance(context)

    fun start() {
        startPrefetchWork()
        startCleanupWork()
    }

    private fun startPrefetchWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val work = OneTimeWorkRequestBuilder<PrefetchWorker>()
            .setConstraints(constraints)
            .setExpedited(
                OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
            )
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        workManager.enqueue(work)
    }

    private fun startCleanupWork() {

        val constraints = Constraints.Builder()
            .setRequiresDeviceIdle(true)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val work = PeriodicWorkRequestBuilder<CleanupWorker>(
            1, TimeUnit.DAYS
        )
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "cleanup_work",
            ExistingPeriodicWorkPolicy.KEEP,
            work
        )
    }
}