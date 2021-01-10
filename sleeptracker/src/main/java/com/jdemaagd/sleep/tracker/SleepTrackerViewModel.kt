package com.jdemaagd.sleep.tracker

import android.app.Application

import androidx.lifecycle.*

import com.jdemaagd.sleep.database.SleepDatabaseDao
import com.jdemaagd.sleep.database.SleepNight
import com.jdemaagd.sleep.formatNights

import kotlinx.coroutines.*

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var tonight = MutableLiveData<SleepNight?>()

    // Note: Room will keep LiveData object updated
    val nights = database.getAllNights()

    // Note: transform/map nights to Spanned
    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    // Note: visible START button if night has NOT been started
    val startButtonVisible = Transformations.map(tonight) { night ->
        null == night
    }

    // Note: visible STOP button if night has been started (set)
    val stopButtonVisible = Transformations.map(tonight) { night ->
        null != night
    }

    // Note: visible CLEAR button if there are nights in database
    val clearButtonVisible = Transformations.map(nights) { nights ->
        nights?.isNotEmpty()
    }

    // Internal navigation event: to tell Fragment to navigate to a specific ` [SleepQualityFragment] `
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

    private val _navigateToSleepDataQuality = MutableLiveData<Long>()

    // Internal snackbar event: request a toast by setting this value to true
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    // Exposed navigation event: to immediately navigate to [SleepQualityFragment]
    //                           if not null and call [doneNavigating]
    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    val navigateToSleepDataQuality
        get() = _navigateToSleepDataQuality

    // Exposed snackbar event: immediately `show()` a toast
    //                         if not null and call `doneShowingSnackbar()`
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    // Call immediately after calling `show()` on a toast
    // clear toast request (prevents duplicate toast on device rotation)
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    // Call immediately after navigating to ` [SleepQualityFragment] `
    // clear navigation request (prevents navigating again on device rotation)
    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    // Note: where ViewModel Lifecycle starts (associated to lifecycle-owner: SleepTrackerFragment)
    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        // Note: leverage lifecycle-aware coroutine scope ViewModelScope provided by AAC
        //       launch coroutine that runs on main UI thread (result affects UI)
        viewModelScope.launch {
            // Note: a database operation happens inside this call (call to suspend function)
            tonight.value = getTonightFromDatabase()
        }
    }

    // Note: does not block UI while doing work (database operation)
    //       validate good night
    private suspend fun getTonightFromDatabase(): SleepNight? {
        // Note: ViewModels provide their own scope by default, which can be accessed by ViewModelScope,
        //       do not need another coroutine in IO context ` withContext(Dispatchers.IO) {} `
        //       inside suspending function definition

        var night = database.getTonight()       // Note: database operation

        // Note: no night started, start-time and end-time will be same when night is started
        //       end-time will update when sleep night is stopped
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }

        return night
    }

    // Note: does not block UI while doing work (database operation)
    private suspend fun clear() {
        // Note: switch to IO context to run in thread pool optimized for these kinds of operations
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    // Note: does not block UI while doing work (database operation)
    private suspend fun update(night: SleepNight) {
        // Note: switch to IO context to run in thread pool optimized for these kinds of operations
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    // Note: does not block UI while doing work (database operation)
    private suspend fun insert(night: SleepNight) {
        // Note: switch to IO context to run in thread pool optimized for these kinds of operations
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    fun onSleepNightClicked(id: Long){
        _navigateToSleepDataQuality.value = id
    }

    fun onSleepDataQualityNavigated() {
        _navigateToSleepDataQuality.value = null
    }

    // Note: start night (sleep)
    fun onStartTracking() {
        // Note: leverage lifecycle-aware coroutine scope ViewModelScope provided by AAC
        //       launch coroutine that runs on main UI thread (result affects UI)
        //       could be time-consuming (long-running task)
        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val newNight = SleepNight()

            // Note: database operation (call to suspend function)
            insert(newNight)

            tonight.value = getTonightFromDatabase()
        }
    }

    // Note: stop sleep
    fun onStopTracking() {
        // Note: leverage lifecycle-aware coroutine scope ViewModelScope provided by AAC
        //       launch coroutine that runs on main UI thread (result affects UI)
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch(),
            // not the lambda.
            val oldNight = tonight.value ?: return@launch

            // Update the night in the database to add the end time
            oldNight.endTimeMilli = System.currentTimeMillis()

            // Note: database operation (call to suspend function)
            update(oldNight)

            // Set state to navigate to SleepQualityFragment
            _navigateToSleepQuality.value = oldNight
        }
    }

    // Note: clear list of sleep data
    fun onClear() {
        // Note: leverage lifecycle-aware coroutine scope ViewModelScope provided by AAC
        //       launch coroutine that runs on main UI thread (result affects UI)
        viewModelScope.launch {
            // Note: database operation (call to suspend function)
            clear()
            tonight.value = null
        }

        _showSnackbarEvent.value = true
    }

    // Note: end of ViewModel Lifecycle (associated with Fragment (lifecycle-owner) termination)
    override fun onCleared() {
        super.onCleared()
        // Note: do not need to cancel all coroutines
        //       as viewModelScope is lifecycle-aware
    }
}
