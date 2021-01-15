package com.jdemaagd.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.jdemaagd.devbyteviewer.database.VideosDatabase
import com.jdemaagd.devbyteviewer.database.asDomainModel
import com.jdemaagd.devbyteviewer.domain.Video
import com.jdemaagd.devbyteviewer.network.Network
import com.jdemaagd.devbyteviewer.network.asDatabaseModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    /**
     * A playlist of videos that can be shown on the screen.
     */
    val videos: LiveData<List<Video>> =
        Transformations.map(database.videoDao.getVideos()) { databaseVideos ->
            databaseVideos.asDomainModel()
        }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the videos for use, observe [videos]
     */
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}