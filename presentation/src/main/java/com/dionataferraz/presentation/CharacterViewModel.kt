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
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.model.CommonItemPresentation
import com.dionataferraz.presentation.model.mapper.toCommonItemPresentation

class CharacterViewModel(
    private val useCase: GetCharacterDetailUseCase,
    private val getComicsDetailUseCase: GetComicsDetailUseCase,
    private val getSeriesDetailUseCase: GetSeriesDetailUseCase
) : ViewModel() {

    private val isVisible = MutableLiveData<Boolean>(false)
    private val characterPresentation = MutableLiveData<CharacterPresentation>()
    private val comicPresentation = MutableLiveData<List<CommonItemPresentation>>()
    private val seriePresentation = MutableLiveData<List<CommonItemPresentation>>()
    private val closeKeyboard = MutableLiveData<Boolean>()
    private val isVisibleClear = MutableLiveData<Boolean>(true)
    private val editText = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()
    private val isEmptyContent = MutableLiveData<Boolean>()

    val error = switchMapToLiveData(errorMessage) { message -> message }
    val characterName = switchMapToLiveData(characterPresentation) { character -> character.name }
    val characterDescription = switchMapToLiveData(characterPresentation) { character -> character.description }
    val characterImage = switchMapToLiveData(characterPresentation) { character -> character.image }
    val isEmptyDescription = switchMapToLiveData(characterDescription) { description -> description.isNotEmpty() }
    val isEmptyComics = switchMapToLiveData(comicPresentation) { comics -> comics.isNotEmpty() }
    val isEmptySeries = switchMapToLiveData(seriePresentation) { series -> series.isNotEmpty() }

    fun closeKeyboard() = switchMapToLiveData(closeKeyboard) { closeKeyboard -> closeKeyboard }
    fun isVisibleClear() = switchMapToLiveData(isVisibleClear) { isVisibleClear -> isVisibleClear }
    fun editText() = switchMapToLiveData(editText) { editText -> editText }
    fun isLoading() = switchMapToLiveData(isVisible) { isVisible -> isVisible }
    fun comicPresentation() = switchMapToLiveData(comicPresentation) { comicPresentation -> comicPresentation }
    fun seriePresentation() = switchMapToLiveData(seriePresentation) { seriePresentation -> seriePresentation }
    fun isEmptyContent() = switchMapToLiveData(isEmptyContent) { isEmptyContent -> isEmptyContent }

    private fun loadCharacters(characterName: String) {
        viewModelScope.launch {
            isVisible.postValue(true)

            try {
                withContext(Dispatchers.IO) {
                    val character = useCase.invoke(characterName).toCharacterPresentation()
                    characterPresentation.postValue(character)
                    comicPresentation.postValue(getComicsDetailUseCase.invoke(character.id).toCommonItemPresentation())
                    seriePresentation.postValue(getSeriesDetailUseCase.invoke(character.id).toCommonItemPresentation())

                    showContent()
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

    private fun showContent(){
        isEmptyContent.postValue(true)
    }
}
