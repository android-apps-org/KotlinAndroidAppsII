# Connect to Internet

##  Think About

```
What is a mobile application that cannot obtain data in real time through the internet?  
While customers demand up to date and reliable data, not all access is created equal.  
Not only do developers have to be concerned with bandwidth due to data rates,  
but also connection speeds or availability altogether.  
Building upon the MVVM model lessons as well as persistence lessons, consider the following:
```
- What information do apps you use provide and where might that come from?
- What parameters do you provide to refine the data sets and find what you need?
- What images are stored within the app natively and what is downloaded remotely?
- Think about your favorite social media app:
  - Do you think your feed comes down all at once?
  - What data does the feed contain? Images?
- Think about a banking application:
  - Do you see all transactions for an account?
  - What parameters might be required to paginate transactions?

## [Permissions](https://developer.android.com/guide/topics/permissions/overview)

- Purpose is to protect privacy of Android user
- Android apps must request permission to access sensitive data
  - i.e. contacts, call logs, system features like camera or internet
- Each publicizes permissions it requires by including uses-permission tags in manifest
- Android 6.0 (marshmallow) introduced runtime permission requests for sensitive data
  - accessing contacts, device camera (6.0+ or API level 23+)
  - must include in manifest and request user to grant permission at runtime
- Normal permissions: uses-permission tags in manifest
- Sensitive Permissions: camera, contacts
- Highly Sensitive Special Permissions: user can only give app permissions within system settings
- SecurityException: permission not set in manifest or user denied permission (runtime)

## [Parcelable](https://developer.android.com/reference/android/os/Parcelable)

- Parceling is a way of sharing objects
  - between different processes
  - by flattening an object into a string of data called a parcel
- A complex object can be stored into the parcel
  - and then recreated from parcel
  - by implementing parcelable interface
  - and they become Parcelable objects
- Each value in the object is written in sequence to the parcel
  - the object is recreated by reading data from the parcel
  - in the same order it was written to populate data in a new object
- Using a parcel to share an object between processes:
  - functionally similar to using XML or JSON
  - to share data between web services and client

## Bundle

- A bundle is a parcelable object that contains a key/value store of parcelable objects
- Bundles used as argument property in fragments
  - primarily because of the way the Android lifecycle works
- Activities will be destroyed with a SaveInstanceState
  - if the app is killed when running in the background
- All of the information in the SaveInstanceState has to be from parcelables
  - since the state is used to recreate objects
  - when the app gets restarted and is therefore a new process
- When an activity is recreated in this state
  - the fragment manager needs to be able to recreate all of the fragments
- Since they are parcelable, bundles can be stored in the SaveInstanceState
  - allowing fragments to preserve their arguments
  - when the process is destroyed and the fragment is recreated

## Resources

- [Retrofit](https://square.github.io/retrofit/)
- [Retrofit on Github](https://github.com/square/retrofit)
- [Connect to Network](https://developer.android.com/training/basics/network-ops/connecting)
- [Request App Permissions](https://developer.android.com/training/permissions/requesting)
- [Permission Best Practices](https://developer.android.com/training/permissions/usage-notes)
- [Custom App Permission](https://developer.android.com/guide/topics/permissions/defining)
- [Moshi JSON library](https://github.com/square/moshi)
- [MoshiConverterFactory](https://square.github.io/retrofit/2.x/converter-moshi/index.html?retrofit2/converter/moshi/MoshiConverterFactory.html)
- [Glide on Github](https://github.com/bumptech/glide)
- [Glide](https://bumptech.github.io/glide/)
- [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel)
- [Parcelable and Bundles](https://developer.android.com/guide/components/activities/parcelables-and-bundles)

