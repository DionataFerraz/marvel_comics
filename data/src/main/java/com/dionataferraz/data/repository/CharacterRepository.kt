package com.dionataferraz.data.repository

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.Comic
import com.dionataferraz.data.model.Serie

interface CharacterRepository {

    suspend fun loadCharacter(characterName: String): Character

    suspend fun loadComics(characterId: Int): List<Comic>

    suspend fun loadSeries(characterId: Int): List<Serie>

}