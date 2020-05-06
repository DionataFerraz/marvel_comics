package com.dionataferraz.presentation

import androidx.lifecycle.*
import com.dionataferraz.core.extensions.switchMapToLiveData
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.model.mapper.toCommonItemPresentation

class CharacterDetailViewModel(
    characterPresentation: CharacterPresentation,
    private val getComicsDetailUseCase: GetComicsDetailUseCase,
    private val getSeriesDetailUseCase: GetSeriesDetailUseCase
) : ViewModel() {

    private val character = MutableLiveData<CharacterPresentation>(characterPresentation)

    val characterName = switchMapToLiveData(character) { character -> character.name }
    val characterDescription = switchMapToLiveData(character) { character -> character.description }
    val characterImage = switchMapToLiveData(character) { character -> character.image }
    val isEmptyName = switchMapToLiveData(characterName) { name -> name.isNotEmpty() }
    val isEmptyDescription = switchMapToLiveData(characterDescription) { description -> description.isNotEmpty() }
    val isEmptyImage = switchMapToLiveData(characterImage) { image -> image.isNotEmpty() }

    private val resourceComics = Transformations.switchMap(character) { character -> getComicsDetailUseCase(character.id) }
    val comics = switchMapToLiveData(resourceComics) { comics -> comics.data?.toCommonItemPresentation() }
    val isLoadingComics = switchMapToLiveData(resourceComics) { resourceComics -> resourceComics is Resource.Loading }
    val isEmptyComics = switchMapToLiveData(comics) { comics -> comics?.isNotEmpty() }

    private val resourceSeries = Transformations.switchMap(character) { character -> getSeriesDetailUseCase(character.id) }
    val series = switchMapToLiveData(resourceSeries) { comics -> comics.data?.toCommonItemPresentation() }
    val isLoadingSeries = switchMapToLiveData(resourceSeries) { resourceSeries -> resourceSeries is Resource.Loading }
    val isEmptySeries = switchMapToLiveData(series) { comics -> comics?.isNotEmpty() }

    val error = MediatorLiveData<NetworkError>().apply {
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
}
