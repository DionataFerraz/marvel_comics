package com.dionataferraz.network

import com.dionataferraz.data.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?apikey=${BuildConfig.PRIVATE_KEY}")
    fun getCharacterAsync(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): Deferred<Response<DataResponse<Results<Character>>>>

    @GET("characters/{characterId}/comics?apikey=${BuildConfig.PRIVATE_KEY}")
    fun getComicsAsync(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Deferred<Response<DataResponse<Results<CommonItem>>>>

    @GET("characters/{characterId}/series?apikey=${BuildConfig.PRIVATE_KEY}")
    fun getSeriesAsync(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Deferred<Response<DataResponse<Results<CommonItem>>>>
}
