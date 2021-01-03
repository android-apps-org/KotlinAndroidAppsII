package com.jdemaagd.sleeptracker.sleep

import android.app.Application

import androidx.lifecycle.AndroidViewModel

import com.jdemaagd.sleeptracker.database.SleepDatabaseDao

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}