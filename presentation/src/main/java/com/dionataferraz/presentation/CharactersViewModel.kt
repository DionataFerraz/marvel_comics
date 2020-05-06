package com.dionataferraz.presentation

import androidx.lifecycle.*
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.core.extensions.switchMapToLiveData
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.presentation.model.mapper.toCharacterPresentation

class CharactersViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val getCharacter = MutableLiveData<String>()

    private val resourceCharacter =
        Transformations.switchMap(getCharacter) { characterName -> getCharacterDetailUseCase(characterName) }
    val characters = switchMapToLiveData(resourceCharacter) { character ->
        character.data?.toCharacterPresentation()
    }
    val isLoadingCharacter =
        switchMapToLiveData(resourceCharacter) { resourceCharacter -> resourceCharacter is Resource.Loading }

    private val closeKeyboard = MutableLiveData<Boolean>()
    private val isVisibleClear = MutableLiveData<Boolean>(true)
    private val editText = MutableLiveData<String>()

    val error = MediatorLiveData<NetworkError>().apply {
        addSource(resourceCharacter) {
            if (it is Resource.Error) {
                value = it.errorData
            }
        }
    }

    fun closeKeyboard() = switchMapToLiveData(closeKeyboard) { closeKeyboard -> closeKeyboard }
    fun isVisibleClear() = switchMapToLiveData(isVisibleClear) { isVisibleClear -> isVisibleClear }
    fun editText() = switchMapToLiveData(editText) { editText -> editText }

    val loadCharacter = fun(characterName: String) {
        closeKeyBoard()
        getCharacter.value = characterName
    }

    val onTextChange = fun(text: String?) {
        text.isNullOrEmpty().run {
            isVisibleClear.value = this
        }
    }

    fun clearText() {
        editText.value = ""
    }

    private fun closeKeyBoard() {
        closeKeyboard.value = true
    }
}
