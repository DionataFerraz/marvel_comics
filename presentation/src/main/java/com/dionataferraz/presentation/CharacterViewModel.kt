package com.dionataferraz.presentation

import android.text.Editable
import androidx.lifecycle.*
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.presentation.extensions.switchMapToLiveData
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.presentation.model.mapper.toCharacterPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.TextView
import android.view.inputmethod.EditorInfo

class CharacterViewModel(private val useCase: GetCharacterDetailUseCase) : ViewModel() {

    private val isVisible = MutableLiveData<Boolean>(false)
    private val characterPresentation = MutableLiveData<CharacterPresentation>()
    private val closeKeyboard = MutableLiveData<Boolean>()
    private val isVisibleClear = MutableLiveData<Boolean>(true)
    private val editText = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()

    val error = switchMapToLiveData(errorMessage) { message -> message }
    val characterName = switchMapToLiveData(characterPresentation) { character -> character.name }
    val characterDescription = switchMapToLiveData(characterPresentation) { character -> character.description }
    val characterImage = switchMapToLiveData(characterPresentation) { character -> character.image }
    val isEmptyCharacter = switchMapToLiveData(characterPresentation) { character -> character.name.isNotEmpty() }

    fun closeKeyboard() = switchMapToLiveData(closeKeyboard) { closeKeyboard -> closeKeyboard }
    fun isVisibleClear() = switchMapToLiveData(isVisibleClear) { isVisibleClear -> isVisibleClear }
    fun isLoading() = switchMapToLiveData(isVisible) { isVisible -> isVisible }
    fun editText() = switchMapToLiveData(editText) { editText -> editText }

    private fun loadCharacters(characterName: String) {
        viewModelScope.launch {
            isVisible.postValue(true)

            try {
                withContext(Dispatchers.IO) {
                    characterPresentation.postValue(useCase.invoke(characterName).toCharacterPresentation())
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }

            isVisible.postValue(false)
        }
    }

    // I didn't really like this
    fun onEditorAction(view: TextView?, actionId: Int?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            view?.run {
                closeKeyBoard()
                loadCharacters(text.toString())
            }
        }
        return false
    }

    fun clearText() {
        editText.value = ""
    }

    fun onTextChange(editable: Editable?): Boolean =
        editable.isNullOrEmpty().apply {
            isVisibleClear.value = this
        }

    private fun closeKeyBoard() {
        closeKeyboard.value = true
    }
}

