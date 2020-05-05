package com.dionataferraz.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.data.CharacterData.THOR
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.presentation.data.CharacterData.CHARACTER_PRESENTATION_THOR
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase = mockk<GetCharacterDetailUseCase>(relaxed = true)
    private var getComicsDetailUseCase = mockk<GetComicsDetailUseCase>(relaxed = true)
    private var getSeriesDetailUseCase = mockk<GetSeriesDetailUseCase>(relaxed = true)

    private lateinit var charactersViewModel: CharacterViewModel
    private val observerString = Observer<String?> {}

    @Before
    @Throws(Exception::class)
    fun setUp() {
        charactersViewModel = CharacterViewModel(useCase, getComicsDetailUseCase, getSeriesDetailUseCase)
    }

    @Test
    fun `should verify character name`() = runBlocking {
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR))

        charactersViewModel.run {
            characterName.run {
                text(THOR.name)
                try {
                    observeForever(observerString)
                    Assert.assertEquals(value, THOR.name)
                } finally {
                    removeObserver(observerString)
                }
            }
        }
    }
}