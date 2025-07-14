package com.pickpick.pickpick.data.album.remote.api

import com.pickpick.pickpick.core.data.remote.BaseResponse
import com.pickpick.pickpick.data.album.remote.dto.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumApi {

    @GET("albums")
    suspend fun getAlbums(
        @Query("search") search: String? = null,
        @Query("cursor") cursor: String? = null,
        @Query("size") size: Int = 20
    ): BaseResponse<List<PhotoResponse>>

    @GET("albums/{albumId}")
    suspend fun getPhoto(@Path("albumId") albumId: String): BaseResponse<PhotoResponse>

    @POST("albums/{albumId}")
    suspend fun deletePhoto(@Path("albumId") albumId: String): BaseResponse<Unit>

}