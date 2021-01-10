package com.jdemaagd.sleep.quality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jdemaagd.sleep.database.SleepDatabaseDao

import kotlinx.coroutines.launch

class SleepQualityViewModel(
        private val sleepNightKey: Long = 0L,
        val database: SleepDatabaseDao) : ViewModel() {

    // Internal navigation event: to tell Fragment to navigate to a specific ` [SleepTrackerFragment] `
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    // Exposed navigation event: to immediately navigate to [SleepTrackerFragment]
    //                           if not null and call [doneNavigating]
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    // Call immediately after navigating to ` [SleepTrackerFragment] `
    // clear navigation request (prevents navigating again on device rotation)
    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    // Note: set sleep quality via icons
    fun onSetSleepQuality(quality: Int) {
        // Note: leverage lifecycle-aware coroutine scope ViewModelScope provided by AAC
        //       launch coroutine that runs on main UI thread (result affects UI)
        viewModelScope.launch {
            val tonight = database.get(sleepNightKey) ?: return@launch
            tonight.sleepQuality = quality

            // Note: database operation (call to suspend function)
            database.update(tonight)

            // Setting this state variable to true will alert the observer and trigger navigation
            _navigateToSleepTracker.value = true
        }
    }
}
