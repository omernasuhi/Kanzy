package com.kanzy.data.repository.music

import com.kanzy.data.remote.service.MusicService
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val musicService: MusicService
) {

    suspend fun searchMusic(
        keyword: String,
        pageToken: String? = null,
        apiKey: String? = null
    ) = musicService.searchMusic(keyword, pageToken, apiKey)

    suspend fun getMusicLink(videoId: String) = musicService.getMusicLink(videoId)

    suspend fun getPlayListMusic(playlistId: String) = musicService.getPlayListMusic(playlistId)

    suspend fun getPopularMusics() = musicService.getPopularMusics()

}