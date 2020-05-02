package com.dionataferraz.presentation

import android.util.Log
import androidx.lifecycle.*
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.presentation.extensions.switchMapToLiveData
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.presentation.model.mapper.toCharacterPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(private val useCase: GetCharacterDetailUseCase) : ViewModel() {

    private val isVisible = MutableLiveData<Boolean>(false)
    private val characterPresentation = MutableLiveData<CharacterPresentation>()

    val characterName = switchMapToLiveData(characterPresentation) { it.name }
    val characterDescription = switchMapToLiveData(characterPresentation) { it.description }
    val characterImage = switchMapToLiveData(characterPresentation) { it.image }

    fun isLoading(): LiveData<Boolean> = isVisible

    fun loadCharacters(characterName: String) {
        viewModelScope.launch {
            isVisible.postValue(true)

            try {
                withContext(Dispatchers.IO) {
                    characterPresentation.postValue(useCase.invoke(characterName).toCharacterPresentation())
                }
            } catch (e: Exception) {
                Log.e("loadCharacters", "CharactersViewModel", e)
            }

            isVisible.postValue(false)
        }
    }
}

