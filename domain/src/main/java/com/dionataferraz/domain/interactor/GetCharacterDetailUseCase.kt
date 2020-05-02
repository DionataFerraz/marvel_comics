package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.model.mapper.toCharacterDetail

class GetCharacterDetailUseCase(private val repository: CharacterRepository) {

    suspend fun invoke(characterName: String): CharacterDetail =
        repository.loadCharacter(characterName = characterName).toCharacterDetail()

}
