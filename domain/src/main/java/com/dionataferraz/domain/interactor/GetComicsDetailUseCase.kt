package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.model.CommonItemDetail
import com.dionataferraz.domain.model.mapper.toCommonItemDetail

class GetComicsDetailUseCase(private val repository: CharacterRepository) {

    suspend fun invoke(characterId: Int): List<CommonItemDetail> =
        repository.loadComics(characterId = characterId).toCommonItemDetail()

}
