package com.pickpick.pickpick.core.di

import com.pickpick.pickpick.data.websocket.StompWebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideStompWebSocketClient(): StompWebSocketClient {
        return StompWebSocketClient()
    }
}