package com.dionataferraz.presentation.di

import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.network.createNetworkClient
import com.dionataferraz.presentation.CharactersViewModel
import com.dionataferraz.domain.datasource.DataSource
import com.dionataferraz.remote.datasource.DataSourceImpl
import com.dionataferraz.network.CharacterService
import com.dionataferraz.domain.repository.CharacterRepository
import com.dionataferraz.presentation.CharacterDetailViewModel
import com.dionataferraz.presentation.model.CharacterPresentation
import com.dionataferraz.remote.repository.CharacterRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

    //Character
    viewModel { CharactersViewModel(getCharacterDetailUseCase = get()) }
    factory { GetCharacterDetailUseCase(repository = get()) }

    //Detail
    viewModel { (characterPresentation: CharacterPresentation) ->
        CharacterDetailViewModel(
            characterPresentation = characterPresentation,
            getComicsDetailUseCase = get(),
            getSeriesDetailUseCase = get()
        )
    }
    factory { GetComicsDetailUseCase(repository = get()) }
    factory { GetSeriesDetailUseCase(repository = get()) }

    factory { CharacterRepositoryImpl(dataSource = get()) } bind CharacterRepository::class

    factory { DataSourceImpl(createNetworkClient().create(CharacterService::class.java)) } bind DataSource::class
}
