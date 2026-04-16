package com.example.msd_hotelreservation

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.msd_hotelreservation.workers.WorkManagerFacade
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()
        WorkManagerFacade(applicationContext).start()
    }
}