package com.kanzy.data.repository.di

import com.kanzy.data.remote.service.MusicService
import com.kanzy.data.repository.music.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideMusicRepository(
        musicService: MusicService
    ): MusicRepository {
        return MusicRepository(musicService)
    }

}