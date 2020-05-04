package com.dionataferraz.remote

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.Comic
import com.dionataferraz.data.model.Serie
import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.remote.helper.AccessFactory.getAccess
import com.dionataferraz.remote.services.CharacterService
import com.dionataferraz.remote.services.RetrofitService

class CharacterRepositoryImpl : CharacterRepository {

    private val retrofitServer by lazy {
        RetrofitService.create<CharacterService>(BuildConfig.HTTP_SERVER)
    }

    private val access by lazy { getAccess(BuildConfig.PUBLIC_KEY + BuildConfig.PRIVATE_KEY) }

    override suspend fun loadCharacter(characterName: String): Character {
        try {
            val response = retrofitServer.getCharacter(
                access.first,
                access.second,
                characterName
            )
            return response.data.results.first()
        } catch (e: Exception) {
            throw  e
        }
    }

    override suspend fun loadComics(characterId: Int): List<Comic> {
        try {
            val response = retrofitServer.getComics(
                characterId,
                access.first,
                access.second
            )
            return response.data.results
        } catch (e: Exception) {
            throw  e
        }
    }

    override suspend fun loadSeries(characterId: Int): List<Serie> {
        try {
            val response = retrofitServer.getSeries(
                characterId,
                access.first,
                access.second
            )
            return response.data.results
        } catch (e: Exception) {
            throw  e
        }
    }

}