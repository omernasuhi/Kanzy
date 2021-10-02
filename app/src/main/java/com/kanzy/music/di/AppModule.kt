package com.kanzy.music.di

import android.content.Context
import com.kanzy.music.features.home.adapter.HomeAdapterFragmentFactory
import com.kanzy.music.features.home.adapter.HomeAdapterFragmentFactoryImpl
import com.kanzy.music.helper.ResourceManager
import com.kanzy.music.helper.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeAdapterFragmentFactoryImpl(): HomeAdapterFragmentFactory {
        return HomeAdapterFragmentFactoryImpl()
    }

    @Provides
    @Singleton
    fun provideResourceManagerImpl(@ApplicationContext context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

}