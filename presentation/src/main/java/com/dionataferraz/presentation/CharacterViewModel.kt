package com.dionataferraz.presentation

import androidx.lifecycle.*
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.core.extensions.switchMapToLiveData
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.model.mapper.toCharacterPresentation
import com.dionataferraz.presentation.model.mapper.toCommonItemPresentation

class CharacterViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getComicsDetailUseCase: GetComicsDetailUseCase,
    private val getSeriesDetailUseCase: GetSeriesDetailUseCase
) : ViewModel() {

    private val getCharacter = MutableLiveData<String>()
    private val characterNotNull = MutableLiveData<CharacterPresentation>()

    private val resourceCharacter = Transformations.switchMap(getCharacter) { characterName -> getCharacterDetailUseCase(characterName) }
    private val character = switchMapToLiveData(resourceCharacter) { character ->
        val char = character.data?.toCharacterPresentation()
        if (char != null) {
            characterNotNull.value = char
        }
        char
    }
    val isLoadingCharacter = switchMapToLiveData(resourceCharacter) { resourceCharacter -> resourceCharacter is Resource.Loading }
    val characterName = switchMapToLiveData(character) { character -> character?.name }
    val characterDescription = switchMapToLiveData(character) { character -> character?.description }
    val characterImage = switchMapToLiveData(character) { character -> character?.image }
    val isEmptyName = switchMapToLiveData(characterName) { name -> name?.isNotEmpty() }
    val isEmptyDescription = switchMapToLiveData(characterDescription) { description -> description?.isNotEmpty() }
    val isEmptyImage = switchMapToLiveData(characterImage) { image -> image?.isNotEmpty() }

    private val resourceComics =
        Transformations.switchMap(characterNotNull) { character -> getComicsDetailUseCase(character.id) }
    val comics = switchMapToLiveData(resourceComics) { comics -> comics.data?.toCommonItemPresentation() }
    val isLoadingComics = switchMapToLiveData(resourceComics) { resourceComics -> resourceComics is Resource.Loading }
    val isEmptyComics = switchMapToLiveData(comics) { comics -> comics?.isNotEmpty() }

    private val resourceSeries =
        Transformations.switchMap(characterNotNull) { character -> getSeriesDetailUseCase(character.id) }
    val series = switchMapToLiveData(resourceSeries) { comics -> comics.data?.toCommonItemPresentation() }
    val isLoadingSeries = switchMapToLiveData(resourceSeries) { resourceSeries -> resourceSeries is Resource.Loading }
    val isEmptySeries = switchMapToLiveData(series) { comics -> comics?.isNotEmpty() }

    private val closeKeyboard = MutableLiveData<Boolean>()
    private val isVisibleClear = MutableLiveData<Boolean>(true)
    private val editText = MutableLiveData<String>()

    val error = MediatorLiveData<NetworkError>().apply {
        addSource(resourceCharacter) {
            if (it is Resource.Error) {
                value = it.errorData
            }
        }

        addSource(resourceComics) {
            if (it is Resource.Error) {
                value = it.errorData
            }
        }

        addSource(resourceSeries) {
            if (it is Resource.Error) {
                value = it.errorData
            }
        }
    }

    fun closeKeyboard() = switchMapToLiveData(closeKeyboard) { closeKeyboard -> closeKeyboard }
    fun isVisibleClear() = switchMapToLiveData(isVisibleClear) { isVisibleClear -> isVisibleClear }
    fun editText() = switchMapToLiveData(editText) { editText -> editText }

    fun loadCharacter(characterName: String) {
        closeKeyBoard()
        getCharacter.value = characterName
    }

    fun clearText() {
        editText.value = ""
    }

    fun onTextChange(text: String?): Boolean =
        text.isNullOrEmpty().apply {
            isVisibleClear.value = this
        }

    private fun closeKeyBoard() {
        closeKeyboard.value = true
    }
}
