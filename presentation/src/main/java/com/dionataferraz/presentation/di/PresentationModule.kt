package com.dionataferraz.presentation.di

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.presentation.CharacterViewModel
import com.dionataferraz.remote.CharacterRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

    //Character
    viewModel { CharacterViewModel(useCase = get()) }
    factory { GetCharacterDetailUseCase(repository = get()) }

    factory { CharacterRepositoryImpl() } bind CharacterRepository::class
}