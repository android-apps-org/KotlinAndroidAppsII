# Sleep Tracker: Persistence

## Think About

- When you open your favorite social media application, what data displays?
- After the initial load, does anything happen? If so, why?
- In applications that require login credentials, do you have to log in every time?
  - How might you accomplish similar behavior?
- In Airplane mode:
  - Do they function? Fully? Partially?
  - What data is still available?
  - How might that be stored?
  - Can you change data or interact with the functions in any way?
  - What happens when you turn airplane mode off?

## Coroutines

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

## Resources

- [Room Annotations](https://developer.android.com/reference/android/arch/persistence/room/package-summary#annotations)
- [Room Library](https://developer.android.com/training/data-storage/room/index.html)
- [Room Database](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
- [Companion objects](https://kotlinlang.org/docs/reference/object-declarations.html#Companion-Objects)
- [Testing](https://developer.android.com/training/testing)
- [Re-using Layouts](https://developer.android.com/training/improving-layouts/reusing-layouts)
- [SimpleDateFormat](https://developer.android.com/reference/java/text/SimpleDateFormat)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Coroutines Code-lab](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0)
- [Coroutines Context and Dispatchers](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html)
- [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/)
- [CData](https://www.w3.org/TR/REC-xml/#sec-cdata-sect)


./gradlew assembleDebug --stacktrace

