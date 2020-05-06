package com.dionataferraz.domain.repository

import androidx.lifecycle.LiveData
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.model.CommonItemDetail

interface CharacterRepository {

    fun loadCharacter(characterName: String): LiveData<Resource<List<CharacterDetail>>>

    fun loadComics(characterId: Int): LiveData<Resource<List<CommonItemDetail>>>

    fun loadSeries(characterId: Int): LiveData<Resource<List<CommonItemDetail>>>

}