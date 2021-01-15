package com.jdemaagd.devbyteviewer

import android.app.Application
import android.os.Build

import androidx.work.*

import com.jdemaagd.devbyteviewer.work.RefreshDataWorker

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import timber.log.Timber

import java.util.concurrent.TimeUnit

/**
 * Override application to setup background work via WorkManager
 */
class DevByteApplication : Application() {

    // Note: take initialization out of onCreate
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    /**
     * called before first screen is shown to user
     *
     * setup any background tasks:
     * running expensive setup operations in background thread to avoid delaying app start
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // Thread.sleep(4_000)
        delayedInit()

        // Note: now user will see first screen right away while app finishes initializing
    }

    // Note: instead of doing initialization before first screen shows, run it in background
    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest
                = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}
