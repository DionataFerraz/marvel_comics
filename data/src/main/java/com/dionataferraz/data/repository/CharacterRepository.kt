package com.dionataferraz.data.repository

import com.dionataferraz.data.model.Character

interface CharacterRepository {

    suspend fun loadCharacter(characterName: String): Character

}