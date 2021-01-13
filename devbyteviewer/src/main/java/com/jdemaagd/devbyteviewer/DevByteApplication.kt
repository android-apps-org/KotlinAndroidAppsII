package com.jdemaagd.devbyteviewer

import android.app.Application
import timber.log.Timber

/**
 * Override application to setup background work via WorkManager
 */
class DevByteApplication : Application() {

    /**
     * called before first screen is shown to user
     *
     * setup any background tasks:
     * running expensive setup operations in background thread to avoid delaying app start
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
