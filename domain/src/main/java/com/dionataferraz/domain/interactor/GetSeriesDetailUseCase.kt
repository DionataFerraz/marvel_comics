package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.model.CommonItemDetail
import com.dionataferraz.domain.model.mapper.toCommonItemDetail

class GetSeriesDetailUseCase(private val repository: CharacterRepository) {

    suspend fun invoke(characterId: Int): List<CommonItemDetail> =
        repository.loadSeries(characterId = characterId).toCommonItemDetail()

}
