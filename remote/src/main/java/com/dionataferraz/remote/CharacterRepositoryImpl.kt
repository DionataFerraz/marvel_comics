package com.dionataferraz.remote

import com.dionataferraz.data.model.Character
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

}