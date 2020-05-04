package com.dionataferraz.remote.services

import com.dionataferraz.data.model.*
import com.dionataferraz.remote.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?apikey=${BuildConfig.PRIVATE_KEY}")
    suspend fun getCharacter(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): DataResponse<Results<Character>>

    @GET("characters/{characterId}/comics?apikey=${BuildConfig.PRIVATE_KEY}")
    suspend fun getComics(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): DataResponse<Results<Comic>>

    @GET("characters/{characterId}/series?apikey=${BuildConfig.PRIVATE_KEY}")
    suspend fun getSeries(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): DataResponse<Results<Serie>>
}
