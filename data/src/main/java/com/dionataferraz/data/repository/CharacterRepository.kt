package com.dionataferraz.data.repository

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.Comic

interface CharacterRepository {

    suspend fun loadCharacter(characterName: String): Character

    suspend fun loadComics(characterId: Int): List<Comic>

}