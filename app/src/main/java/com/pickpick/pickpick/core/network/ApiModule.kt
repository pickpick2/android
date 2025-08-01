package com.pickpick.pickpick.core.network

import com.pickpick.pickpick.data.album.remote.api.AlbumApi
import com.pickpick.pickpick.data.pick.remote.api.PickApi
import com.pickpick.pickpick.data.auth.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAlbumApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): AlbumApi = retrofit.create(AlbumApi::class.java)

    @Provides
    @Singleton
    fun providePickApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): PickApi = retrofit.create(PickApi::class.java)
    @Provides
    @Singleton
    fun provideAuthApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

}
