package com.dionataferraz.remote.datasource

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.data.model.DataResponse
import com.dionataferraz.data.model.Results
import com.dionataferraz.domain.datasource.DataSource
import com.dionataferraz.network.CharacterService
import com.dionataferraz.remote.BuildConfig
import com.dionataferraz.remote.dsl.networkCall
import com.dionataferraz.remote.helper.AccessFactory

class DataSourceImpl(
    private val service: CharacterService
) : DataSource {

    private val access by lazy { AccessFactory.getAccess(BuildConfig.PUBLIC_KEY + BuildConfig.PRIVATE_KEY) }

    override fun getCharacter(characterName: String) = networkCall<DataResponse<Results<Character>>> {
        client = service.getCharacterAsync(access.first, access.second, characterName)
    }

    override fun getComics(characterId: Int) = networkCall<DataResponse<Results<CommonItem>>> {
        client = service.getComicsAsync(characterId, access.first, access.second)
    }

    override fun getSeries(characterId: Int) = networkCall<DataResponse<Results<CommonItem>>> {
        client = service.getSeriesAsync(characterId, access.first, access.second)
    }

}
