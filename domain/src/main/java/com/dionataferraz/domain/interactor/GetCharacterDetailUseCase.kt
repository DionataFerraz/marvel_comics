package com.dionataferraz.domain.interactor

import androidx.lifecycle.LiveData
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(private val repository: CharacterRepository) {

    operator fun invoke(characterName: String): LiveData<Resource<CharacterDetail>> =
        repository.loadCharacter(characterName = characterName)

}
