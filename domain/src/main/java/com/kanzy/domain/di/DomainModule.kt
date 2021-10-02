package com.kanzy.domain.di

import com.kanzy.data.repository.music.MusicRepository
import com.kanzy.domain.music.SearchMusic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideSearchMusic(repository: MusicRepository): SearchMusic {
        return SearchMusic(repository)
    }

}