package com.dionataferraz.domain.interactor

import androidx.lifecycle.LiveData
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.model.CommonItemDetail
import com.dionataferraz.domain.repository.CharacterRepository

class GetComicsDetailUseCase(private val repository: CharacterRepository) {

    operator fun invoke(characterId: Int): LiveData<Resource<List<CommonItemDetail>>> =
        repository.loadComics(characterId = characterId)

}
