package com.dionataferraz.remote.services

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.DataResponse
import com.dionataferraz.data.model.Results
import com.dionataferraz.remote.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?apikey=${BuildConfig.PRIVATE_KEY}")
    suspend fun getCharacter(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): DataResponse<Results<Character>>
}
