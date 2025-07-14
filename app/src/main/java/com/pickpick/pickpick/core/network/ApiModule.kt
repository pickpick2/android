package com.pickpick.pickpick.core.network

import com.pickpick.pickpick.data.album.remote.api.AlbumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUserApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): AlbumApi = retrofit.create(AlbumApi::class.java)


}
