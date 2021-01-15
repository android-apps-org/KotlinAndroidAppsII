# Offline Caching

## Think About
```
What is a mobile application that cannot obtain data in real time through the internet?
While customers demand up to date and reliable data, not all access is created equal.  
Not only do developers have to be concerned with bandwidth due to data rates,  
but also connection speeds or availability altogether.  
Building upon the MVVM model lessons as well as persistence lessons, consider following:
```

- What information do apps you use provide and where might that come from?
- What parameters do you provide to filter the results?
- When you enter data, what might the POST call look like?
- Are you able to enter data offline or maybe cellular data, but then back up when on WiFi?
- How would you go about achieving that behavior?

## Cache

- Copy data closer to where it will be used for faster accessibility
- i.e. Browser has cache to store every web page visited on local file system
  - so it can load resources locally next time web page is visited
- Android app should cache data on file system
  - every Android app has access to folder to store files and data
- Retrofit caching is like web browser:
  - every time you get network result keep copy on disk
  - next time you query at same place, load stored copy from disk
  - you can configure to do this caching for you (turn this on for production)
- Most apps need to implement a more sophisticated type of caching
- Cache invalidation: knowing when data in cache is stale
- Network requests over HTTP have a bunch of features to help with this
  - when network caching is enabled in Retrofit, it will use all of them
- As complexity grows more structure is needed for cache
  - i.e. data may be combination of several network requests
  - or backend is written in way you cannot use Retrofit caching
- SQL database:
  - best way to store structured data in an offline cache
  - to give control over how data is stored and invalidated
  - instead of hosting database on server somewhere, keep it locally on file system
- Room: library for working with databases on Android
  - save/load Kotlin data classes to local database (offline cache)
  - Shared Preferences: not a great fit
    - limited to storing keys/values
    - is not a great way to store a lot of data or structured data
    - cannot do complex queries against offline cache
- Files: can access app folders and write an file(s) you want to it
  - private to your app
  - will be cleared when app is uninstalled
  - great for specific need solved by using file system
    - i.e. saving an image or data file
  - but means managing files yourself

## Data Objects

- Domain objects
  - core data representation of app
- Data transfer objects
  - responsible for parsing and representing values on network
- Database objects
  - entirely for interacting with database

## [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)

- To do background work: Upload logs, process data, upload metrics, pre-fetch content, etc.
- Lightweight API
- Schedule background work
- MyWork: Worker()
- Pre-fetch content
- Do not tell how WorkManager how to do work but under what constraints:
  - i.e. charging, device idle, on wifi, use not using device, etc.
- Background work is one of largest users of battery
  - if app uses too much battery will get lower ranking in play-store
  - efficient battery usage is important to success of app
- Battery Friendly: optimized (CPU, Disk), low network, work runs rarely, ... or not at all
- Pre-fetching:
  - load what users need next
  - in background
  - typically done nightly
  - WorkManager schedules efficiently

## Resources

- [Data Storage](https://developer.android.com/training/data-storage)
- [Save via Room](https://developer.android.com/training/data-storage/room)
- [Fetch Data](https://developer.android.com/jetpack/guide#fetch-data)
- [Connect ViewModel and Repository](https://developer.android.com/jetpack/guide#connect-viewmodel-repository)
- [WorkManager Intro](https://medium.com/androiddevelopers/introducing-workmanager-2083bcfc4712)
- [WorkManager CodeLab](https://developer.android.com/codelabs/android-workmanager#0)
- [WorkManager Basics](https://developer.android.com/topic/libraries/architecture/workmanager/basics)
- [Worker](https://developer.android.com/reference/androidx/work/Worker)


