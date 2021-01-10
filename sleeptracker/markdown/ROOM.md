# [Room Library](https://developer.android.com/training/data-storage/room/index.html)

## Room Overview

- Database layer on top of SQLite database
- Query syntax follows SQLite model
- Entity: object or concept to store in database
  - Entity class defines a table
  - each instance is stored as a table row
  - each property represents a column
- DAO: custom interface for accessing database
  - mappings to SQL queries
- Compile-time error checking
- LiveData: updated whenever database is updated
  - only have to get once (i.e. initial load)
  - attach observer to LiveData
  - ui will update itself to reflect changes (without having to get data again)

## ViewModel

- Interacts with database via DAO
- Provide data to UI via LiveData
- Database operations will be run away from UI thread via coroutines

## Resources

- [Room Database](https://developer.android.com/reference/androidx/room/Database)
- [Database Annotations](https://developer.android.com/reference/androidx/room/package-summary)
- [Raw Query](https://developer.android.com/reference/androidx/room/RawQuery)
- [Database Builder](https://developer.android.com/reference/androidx/room/RoomDatabase.Builder)
- [Migrating Database](https://developer.android.com/training/data-storage/room/migrating-db-versions)
- [On properly using volatile and synchronized](https://medium.com/google-developer-experts/on-properly-using-volatile-and-synchronized-702fc05faac2)
- [companion objects](https://kotlinlang.org/docs/reference/object-declarations.html)
- [Understanding Migrations with Room](https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929)
- [Testing Migrations](https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975)
- [Test Apps on Android](https://developer.android.com/training/testing)
- [merge - reuse layouts](https://developer.android.com/training/improving-layouts/reusing-layouts)

