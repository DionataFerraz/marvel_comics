package com.dionataferraz.data.repository

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem

interface CharacterRepository {

    suspend fun loadCharacter(characterName: String): Character

    suspend fun loadComics(characterId: Int): List<CommonItem>

    suspend fun loadSeries(characterId: Int): List<CommonItem>

}