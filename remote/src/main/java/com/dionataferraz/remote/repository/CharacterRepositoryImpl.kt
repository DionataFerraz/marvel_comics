package com.dionataferraz.remote.repository

import com.dionataferraz.core.extensions.switchMapToLiveData
import com.dionataferraz.domain.repository.CharacterRepository
import com.dionataferraz.domain.datasource.DataSource
import com.dionataferraz.domain.model.mapper.toCharacterDetail
import com.dionataferraz.domain.model.mapper.toCommonItemDetail

class CharacterRepositoryImpl(
    private val dataSource: DataSource
) : CharacterRepository {

    override fun loadCharacter(characterName: String) =
        switchMapToLiveData(dataSource.getCharacter(characterName)) { resource ->
            resource.resourceType { response ->
                response?.run {
                    data.results.first().toCharacterDetail()
                }
            }
        }

    override fun loadComics(characterId: Int) =
        switchMapToLiveData(dataSource.getComics(characterId)) { resource ->
            resource.resourceType { response ->
                response?.run {
                    data.results.toCommonItemDetail()
                }
            }
        }

    override fun loadSeries(characterId: Int) =
        switchMapToLiveData(dataSource.getSeries(characterId)) { resource ->
            resource.resourceType { response ->
                response?.run {
                    data.results.toCommonItemDetail()
                }
            }
        }
}