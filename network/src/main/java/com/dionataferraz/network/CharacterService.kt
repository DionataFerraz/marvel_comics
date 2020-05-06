package com.dionataferraz.network

import com.dionataferraz.data.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?apikey=${BuildConfig.PRIVATE_KEY}&ts=${BuildConfig.TS}&hash=${BuildConfig.HASH}")
    fun getCharacterAsync(
        @Query("nameStartsWith") nameStartsWith: String
    ): Deferred<Response<DataResponse<Results<Character>>>>

    @GET("characters/{characterId}/comics?apikey=${BuildConfig.PRIVATE_KEY}&ts=${BuildConfig.TS}&hash=${BuildConfig.HASH}")
    fun getComicsAsync(
        @Path("characterId") characterId: Int
    ): Deferred<Response<DataResponse<Results<CommonItem>>>>

    @GET("characters/{characterId}/series?apikey=${BuildConfig.PRIVATE_KEY}&ts=${BuildConfig.TS}&hash=${BuildConfig.HASH}")
    fun getSeriesAsync(
        @Path("characterId") characterId: Int
    ): Deferred<Response<DataResponse<Results<CommonItem>>>>
}
