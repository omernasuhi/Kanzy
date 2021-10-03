package com.kanzy.data.remote.service

import com.kanzy.data.remote.model.musiclink.MusicLinkResponse
import com.kanzy.data.remote.model.playlistmusic.PlayListMusicResponse
import com.kanzy.data.remote.model.searchmusic.SearchMusic
import com.kanzy.data.remote.model.searchmusic.SearchMusicResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

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

    @GET(POPULAR_MUSIC)
    suspend fun getPopularMusics(): List<SearchMusic>

    companion object {
        const val SEARCH_MUSIC = "searchMusic"
        const val MUSIC_LINK = "musicLink"
        const val PLAY_LIST_MUSIC = "playListMusicItem"
        const val POPULAR_MUSIC = "https://kanzy-music-default-rtdb.firebaseio.com/popular.json"
    }
}