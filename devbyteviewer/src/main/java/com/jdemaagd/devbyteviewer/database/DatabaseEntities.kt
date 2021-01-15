package com.jdemaagd.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jdemaagd.devbyteviewer.domain.Video

@Entity
data class DatabaseVideo constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)

// Note: extension converts database object to domain object
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map { databaseVideo ->
        Video (
            url = databaseVideo.url,
            title = databaseVideo.title,
            description = databaseVideo.description,
            updated = databaseVideo.updated,
            thumbnail = databaseVideo.thumbnail)
    }
}
