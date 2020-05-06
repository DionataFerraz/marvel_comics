package com.dionataferraz.remote.datasource

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.data.model.DataResponse
import com.dionataferraz.data.model.Results
import com.dionataferraz.domain.datasource.DataSource
import com.dionataferraz.network.CharacterService
import com.dionataferraz.remote.dsl.networkCall

class DataSourceImpl(
    private val service: CharacterService
) : DataSource {

    override fun getCharacter(characterName: String) = networkCall<DataResponse<Results<Character>>> {
        client = service.getCharacterAsync(characterName)
    }

    override fun getComics(characterId: Int) = networkCall<DataResponse<Results<CommonItem>>> {
        client = service.getComicsAsync(characterId)
    }

    override fun getSeries(characterId: Int) = networkCall<DataResponse<Results<CommonItem>>> {
        client = service.getSeriesAsync(characterId)
    }

}
