package com.kanzy.data.remote.service

import com.kanzy.data.remote.model.musiclink.MusicLinkResponse
import com.kanzy.data.remote.model.playlistmusic.PlayListMusicResponse
import com.kanzy.data.remote.model.searchmusic.SearchMusicResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET(SEARCH_MUSIC)
    suspend fun searchMusic(
        @Query("keyword") keyword: String,
        @Query("pageToken") pageToken: String? = null,
        @Query("apiKey") apiKey: String? = null
    ): SearchMusicResponse

    @GET(MUSIC_LINK)
    suspend fun getMusicLink(
        @Query("videoId") videoId: String
    ): MusicLinkResponse

    @GET(PLAY_LIST_MUSIC)
    suspend fun getPlayListMusic(
        @Query("playlistId") playlistId: String
    ): PlayListMusicResponse

    companion object {
        const val SEARCH_MUSIC = "searchMusic"
        const val MUSIC_LINK = "musicLink"
        const val PLAY_LIST_MUSIC = "playListMusicItem"
    }
}