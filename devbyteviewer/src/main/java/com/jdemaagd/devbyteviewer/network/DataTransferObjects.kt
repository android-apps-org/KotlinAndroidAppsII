package com.jdemaagd.devbyteviewer.network

import com.jdemaagd.devbyteviewer.database.DatabaseVideo
import com.jdemaagd.devbyteviewer.domain.Video
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file.
 * These are responsible for parsing responses from the server
 *   or formatting objects to send to the server
 * You should convert these to domain objects before using them
 */

/**
 * VideoHolder holds a list of Videos
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "videos": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

/**
 * Videos represent a dev-byte that can be played
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
        val title: String,
        val description: String,
        val url: String,
        val updated: String,
        val thumbnail: String,
        val closedCaptions: String?)

// Note: extension convert dto to domain object
fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map { networkVideo ->
        Video(
            title = networkVideo.title,
            description = networkVideo.description,
            url = networkVideo.url,
            updated = networkVideo.updated,
            thumbnail = networkVideo.thumbnail)
    }
}

// Note: extension converts from dto to database object
fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map { networkVideo ->
        DatabaseVideo (
            title = networkVideo.title,
            description = networkVideo.description,
            url = networkVideo.url,
            updated = networkVideo.updated,
            thumbnail = networkVideo.thumbnail)
    }.toTypedArray()
}
