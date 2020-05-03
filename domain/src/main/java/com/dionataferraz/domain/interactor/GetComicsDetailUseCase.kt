package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.model.ComicDetail
import com.dionataferraz.domain.model.mapper.toComicsDetail

class GetComicsDetailUseCase(private val repository: CharacterRepository) {

    suspend fun invoke(characterId: Int): List<ComicDetail> =
        repository.loadComics(characterId = characterId).toComicsDetail()

}
