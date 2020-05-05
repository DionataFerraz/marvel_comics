package com.dionataferraz.domain.datasource

import androidx.lifecycle.LiveData
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.data.model.DataResponse
import com.dionataferraz.data.model.Results

interface DataSource {

    fun getCharacter(characterName: String): LiveData<Resource<DataResponse<Results<Character>>>>

    fun getComics(characterId: Int): LiveData<Resource<DataResponse<Results<CommonItem>>>>

    fun getSeries(characterId: Int): LiveData<Resource<DataResponse<Results<CommonItem>>>>

}
