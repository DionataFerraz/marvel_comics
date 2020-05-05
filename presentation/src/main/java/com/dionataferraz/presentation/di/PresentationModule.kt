package com.dionataferraz.presentation.di

import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.network.createNetworkClient
import com.dionataferraz.presentation.CharacterViewModel
import com.dionataferraz.domain.datasource.DataSource
import com.dionataferraz.remote.datasource.DataSourceImpl
import com.dionataferraz.network.CharacterService
import com.dionataferraz.domain.repository.CharacterRepository
import com.dionataferraz.remote.repository.CharacterRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

    //Character
    viewModel {
        CharacterViewModel(
            getCharacterDetailUseCase = get(),
            getComicsDetailUseCase = get(),
            getSeriesDetailUseCase = get()
        )
    }

    factory { GetCharacterDetailUseCase(repository = get()) }
    factory { GetComicsDetailUseCase(repository = get()) }
    factory { GetSeriesDetailUseCase(repository = get()) }

    factory { CharacterRepositoryImpl(dataSource = get()) } bind CharacterRepository::class

    factory { DataSourceImpl(createNetworkClient().create(CharacterService::class.java)) } bind DataSource::class
}
