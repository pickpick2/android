package com.pickpick.pickpick.core.di

import com.pickpick.pickpick.data.album.remote.repository.AlbumRepositoryImpl
import com.pickpick.pickpick.data.pick.remote.repository.PickRepositoryImpl
import com.pickpick.pickpick.data.websocket.WebSocketRepositoryImpl
import com.pickpick.pickpick.data.auth.remote.repository.AuthRepositoryImpl
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import com.pickpick.pickpick.domain.pick.repository.PickRepository
import com.pickpick.pickpick.domain.websocket.repository.WebSocketRepository
import com.pickpick.pickpick.domain.auth.repository.AuthRepository
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

    @Binds
    @Singleton
    abstract fun bindPickRemoteRepository(
        pickRepositoryImpl: PickRepositoryImpl
    ): PickRepository

    @Binds
    @Singleton
    abstract fun bindWebSocketRepository(
        webSocketRepositoryImpl: WebSocketRepositoryImpl
    ): WebSocketRepository

    @Binds
    @Singleton
    abstract fun bindAuthRemoteRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

}
