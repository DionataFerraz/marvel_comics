package com.dionataferraz.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.common.verifyBoolean
import com.dionataferraz.presentation.common.verifyBooleanNotNull
import com.dionataferraz.presentation.common.verifyError
import com.dionataferraz.presentation.common.verifyStringNotNull
import com.dionataferraz.presentation.data.CharacterData.CHARACTER_PRESENTATION_THOR
import com.dionataferraz.presentation.data.CharacterData.CHARACTER_PRESENTATION_THOR_EMPTY
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_DETAIL_COMICS_THOR
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_DETAIL_SERIES_THOR
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_EMPTY
import com.dionataferraz.presentation.data.CharacterData.THOR
import com.dionataferraz.presentation.data.CharacterData.THOR_COMICS
import com.dionataferraz.presentation.data.CharacterData.THOR_SERIES
import com.dionataferraz.presentation.data.ErrorData.CONNECTION
import com.dionataferraz.presentation.data.ErrorData.FAILURE
import com.dionataferraz.presentation.data.ErrorData.RESPONSE
import com.dionataferraz.presentation.data.ErrorData.TIME_OUT
import com.dionataferraz.presentation.data.ErrorData.UNAUTHORIZED
import com.dionataferraz.presentation.model.CommonItemPresentation
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getComicsDetailUseCase = mockk<GetComicsDetailUseCase>(relaxed = true)
    private var getSeriesDetailUseCase = mockk<GetSeriesDetailUseCase>(relaxed = true)

    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private val observerCommonPresentation = Observer<List<CommonItemPresentation>?> {}

    @Before
    @Throws(Exception::class)
    fun setUp() {
        characterDetailViewModel =
            CharacterDetailViewModel(CHARACTER_PRESENTATION_THOR, getComicsDetailUseCase, getSeriesDetailUseCase)
    }

    @Test
    fun `should verify character name`() = runBlocking {
        characterDetailViewModel.run {
            verifyStringNotNull(characterName, THOR.name)
        }
    }

    @Test
    fun `should verify character description`() = runBlocking {
        characterDetailViewModel.run {
            verifyStringNotNull(characterDescription, THOR.description)
        }
    }

    @Test
    fun `should verify character image`() = runBlocking {
        characterDetailViewModel.run {
            verifyStringNotNull(characterImage, THOR.image)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyName is true`() = runBlocking {
        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyName, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyName is false`() = runBlocking {
        characterDetailViewModel =
            CharacterDetailViewModel(CHARACTER_PRESENTATION_THOR_EMPTY, getComicsDetailUseCase, getSeriesDetailUseCase)

        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyName, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyDescription is true`() = runBlocking {
        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyDescription, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyDescription is false`() = runBlocking {
        characterDetailViewModel =
            CharacterDetailViewModel(CHARACTER_PRESENTATION_THOR_EMPTY, getComicsDetailUseCase, getSeriesDetailUseCase)

        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyDescription, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyImage is true`() = runBlocking {
        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyImage, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyImage is false`() = runBlocking {
        characterDetailViewModel =
            CharacterDetailViewModel(CHARACTER_PRESENTATION_THOR_EMPTY, getComicsDetailUseCase, getSeriesDetailUseCase)

        characterDetailViewModel.run {
            verifyBooleanNotNull(isEmptyImage, false)
        }
    }

    @Test
    fun `should verify comics data`() = runBlocking {
        coEvery { getComicsDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        characterDetailViewModel.run {
            comics.run {
                try {
                    observeForever(observerCommonPresentation)
                    assertEquals(value, COMMON_ITEM_DETAIL_COMICS_THOR)
                } finally {
                    removeObserver(observerCommonPresentation)
                }
            }
        }
    }

    @Test
    fun `should verify if isLoadingComics is true`() = runBlocking {
        coEvery { getComicsDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Loading())
        characterDetailViewModel.run {
            verifyBooleanNotNull(isLoadingComics, true)
        }
    }

    @Test
    fun `should verify if isLoadingComics is false`() = runBlocking {
        coEvery { getComicsDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        characterDetailViewModel.run {
            verifyBooleanNotNull(isLoadingComics, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyComics is false`() = runBlocking {
        coEvery { getComicsDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(
            Resource.Success(
                COMMON_ITEM_EMPTY
            )
        )
        characterDetailViewModel.run {
            verifyBoolean(isEmptyComics, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyComics is true`() = runBlocking {
        coEvery { getComicsDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        characterDetailViewModel.run {
            verifyBoolean(isEmptyComics, true)
        }
    }

    @Test
    fun `should verify series data`() = runBlocking {
        coEvery { getSeriesDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_SERIES))
        characterDetailViewModel.run {
            series.run {
                try {
                    observeForever(observerCommonPresentation)
                    assertEquals(value, COMMON_ITEM_DETAIL_SERIES_THOR)
                } finally {
                    removeObserver(observerCommonPresentation)
                }
            }
        }
    }

    @Test
    fun `should verify if isLoadingSeries is true`() = runBlocking {
        coEvery { getSeriesDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Loading())
        characterDetailViewModel.run {
            verifyBooleanNotNull(isLoadingSeries, true)
        }
    }

    @Test
    fun `should verify if isLoadingSeries is false`() = runBlocking {
        coEvery { getSeriesDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        characterDetailViewModel.run {
            verifyBooleanNotNull(isLoadingSeries, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptySeries is false`() = runBlocking {
        coEvery { getSeriesDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(
            Resource.Success(
                COMMON_ITEM_EMPTY
            )
        )
        characterDetailViewModel.run {
            verifyBoolean(isEmptySeries, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptySeries is true`() = runBlocking {
        coEvery { getSeriesDetailUseCase(CHARACTER_PRESENTATION_THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        characterDetailViewModel.run {
            verifyBoolean(isEmptySeries, true)
        }
    }

    @Test
    fun `should verify getComics CONNECTION error`() = runBlocking {
        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.CONNECTION)))
        testError(CONNECTION)
    }

    @Test
    fun `should verify getComics UNAUTHORIZED error`() = runBlocking {
        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))
        testError(UNAUTHORIZED)
    }

    @Test
    fun `should verify getComics TIME_OUT error`() = runBlocking {
        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))
        testError(TIME_OUT)
    }

    @Test
    fun `should verify getComics RESPONSE error`() = runBlocking {
        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))
        testError(RESPONSE)
    }

    @Test
    fun `should verify getComics FAILURE error`() = runBlocking {
        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))
        testError(FAILURE)
    }

    @Test
    fun `should verify getSeries CONNECTION error`() = runBlocking {
        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.CONNECTION)))
        testError(CONNECTION)
    }

    @Test
    fun `should verify getSeries UNAUTHORIZED error`() = runBlocking {
        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))
        testError(UNAUTHORIZED)
    }

    @Test
    fun `should verify getSeries TIME_OUT error`() = runBlocking {
        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))
        testError(TIME_OUT)
    }

    @Test
    fun `should verify getSeries RESPONSE error`() = runBlocking {
        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))
        testError(RESPONSE)
    }

    @Test
    fun `should verify getSeries FAILURE error`() = runBlocking {
        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))
        testError(FAILURE)
    }

    private fun testError(valueExpcted: NetworkError) {
        verifyError(characterDetailViewModel.error, valueExpcted)
    }

}