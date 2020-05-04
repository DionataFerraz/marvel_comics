package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.model.SerieDetail
import com.dionataferraz.domain.model.mapper.toSerieDetail

class GetSeriesDetailUseCase(private val repository: CharacterRepository) {

    suspend fun invoke(characterId: Int): List<SerieDetail> =
        repository.loadSeries(characterId = characterId).toSerieDetail()

}
