package com.pickpick.pickpick.core.di

import com.pickpick.pickpick.data.album.remote.repository.AlbumRepositoryImpl
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    // Remote
    @Binds
    @Singleton
    abstract fun bindAlbumRemoteRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

}
