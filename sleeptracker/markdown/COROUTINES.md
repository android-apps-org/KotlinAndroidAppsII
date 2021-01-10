# [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)

## Multi-threading

- Mobile devices have multiple hardware processors that each run multiple processes concurrently
- To use processors more efficiently,
  - the OS can enable an application to create more than one thread of execution within a process
- Scheduler takes into account priorities, etc. to make sure all threads get to run and finish
- Dispatcher sets up threads and specifies context
- For smoothly running UI:
  - main thread has to update screen every 16ms (60 fps)
  - at this speed humans perceive change of frames as completely smooth

## Coroutines Overview

- Convert callback-based code to sequential code
  - can use languages features such as exceptions
  - with callbacks there is not way to signal an error from long-running task with exceptions
    - as flow control has already left the function that registered to callback
    - because callback is not typically called by application code
    - callbacks need to pass errors via another exception handler
      - called an airbag or result object (success/failure)
- Characteristics:
  - Asynchronous
    - coroutines run independently from main execution steps of program
    - could be in parallel, on separate processor,
  - Non-blocking
    - system is not blocking the main or UI thread
  - Sequential code via suspend functions
    - suspend: way to make function or function type available to coroutines
    - instead of blocking until function return like normal function call
      - it suspends execution until result is ready
      - then resumes where it left off with the result
      - while its suspended, it unlocks the threads its running on
        - so other functions or coroutines can run
- Requirements:
  - Job: background job
    - anything that can be cancelled with a lifecycle that culminates in its completion
    - since jobs can be cancelled, use to cancel coroutine
  - Dispatcher: sends off coroutines to run on various threads
  - Scope: combines information
    - including a job and dispatcher to define the context in which the coroutine runs

## [Navigation](https://developer.android.com/guide/navigation)

- User presses STOP (at SleepTrackerFragment)
- Navigate to SleepQualityFragment
- Pass data with safeArgs (to update correct sleep night)
- User taps quality icon
- Update database (update current night)
- Return to SleepTrackerFragment
- Handle back button
- How:
  - navigation should happen in Fragment
  - click-handler is in ViewModel processing data
  - create navigationEvent variable in ViewModel
  - observe this event
  - when it changes navigate immediately
  - clear navigation variable to signify navigating is finished

## Resources

- [Coroutines lab](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0)
- [Context and Dispatchers](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html)
- [Dispatchers](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html)
- [Android Speed Limit](https://medium.com/androiddevelopers/exceed-the-android-speed-limit-b73a0692abc1)
- [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/)
- [launch](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html)
- [HtmlCompat](https://developer.android.com/reference/androidx/core/text/HtmlCompat)
- [Kotlin Return Labels](https://kotlinlang.org/docs/reference/returns.html#return-at-labels)
- [CData](https://www.w3.org/TR/REC-xml/#sec-cdata-sect)
- [SimpleDateFormat](https://developer.android.com/reference/java/text/SimpleDateFormat)
- [Transformations](https://developer.android.com/codelabs/kotlin-android-training-live-data-transformations#0)
- [Design Navigation Graph](https://developer.android.com/guide/navigation/navigation-design-graph)
- [Bind layout views to Architecture Components](https://developer.android.com/topic/libraries/data-binding/architecture)

