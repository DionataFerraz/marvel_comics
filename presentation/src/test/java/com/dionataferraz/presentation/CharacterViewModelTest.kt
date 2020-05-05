package com.dionataferraz.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.domain.interactor.GetComicsDetailUseCase
import com.dionataferraz.domain.interactor.GetSeriesDetailUseCase
import com.dionataferraz.presentation.data.CharacterData.THOR
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_DETAIL_COMICS_THOR
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_DETAIL_SERIES_THOR
import com.dionataferraz.presentation.data.CharacterData.COMMON_ITEM_EMPTY
import com.dionataferraz.presentation.data.CharacterData.THOR_COMICS
import com.dionataferraz.presentation.data.CharacterData.THOR_EMPTY
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

class CharacterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase = mockk<GetCharacterDetailUseCase>(relaxed = true)
    private var getComicsDetailUseCase = mockk<GetComicsDetailUseCase>(relaxed = true)
    private var getSeriesDetailUseCase = mockk<GetSeriesDetailUseCase>(relaxed = true)

    private lateinit var charactersViewModel: CharacterViewModel
    private val observerString = Observer<String?> {}
    private val observerBoolean = Observer<Boolean?> {}
    private val observerNetworkError = Observer<NetworkError?> {}
    private val observerCommonPresentation = Observer<List<CommonItemPresentation>?> {}

    @Before
    @Throws(Exception::class)
    fun setUp() {
        charactersViewModel = CharacterViewModel(useCase, getComicsDetailUseCase, getSeriesDetailUseCase)
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR))
    }

    @Test
    fun `should verify character name`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyString(characterName, THOR.name)
        }
    }

    @Test
    fun `should verify character description`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyString(characterDescription, THOR.description)
        }
    }

    @Test
    fun `should verify character image`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyString(characterImage, THOR.image)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyName is true`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyName, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyName is false`() = runBlocking {
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR_EMPTY))

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyName, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyDescription is true`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyDescription, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyDescription is false`() = runBlocking {
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR_EMPTY))
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyDescription, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyImage is true`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyImage, true)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyImage is false`() = runBlocking {
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR_EMPTY))
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(isEmptyImage, false)
        }
    }

    @Test
    fun `should verify if isLoadingCharacter is false`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBooleanNotNull(isLoadingCharacter, false)
        }
    }

    @Test
    fun `should verify if isLoadingCharacter is true`() = runBlocking {
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Loading())
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBooleanNotNull(isLoadingCharacter, true)
        }
    }

    @Test
    fun `should verify comics data`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        charactersViewModel.run {
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
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Loading())
        charactersViewModel.run {
            verifyBooleanNotNull(isLoadingComics, true)
        }
    }

    @Test
    fun `should verify if isLoadingComics is false`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        charactersViewModel.run {
            verifyBooleanNotNull(isLoadingComics, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyComics is false`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(COMMON_ITEM_EMPTY))
        charactersViewModel.run {
            verifyBoolean(isEmptyComics, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptyComics is true`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        charactersViewModel.run {
            verifyBoolean(isEmptyComics, true)
        }
    }

    @Test
    fun `should verify series data`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_SERIES))
        charactersViewModel.run {
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
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Loading())
        charactersViewModel.run {
            verifyBooleanNotNull(isLoadingSeries, true)
        }
    }

    @Test
    fun `should verify if isLoadingSeries is false`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        charactersViewModel.run {
            verifyBooleanNotNull(isLoadingSeries, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptySeries is false`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(COMMON_ITEM_EMPTY))
        charactersViewModel.run {
            verifyBoolean(isEmptySeries, false)
        }
    }

    @Test
    fun `should verify if isNotEmpty val isEmptySeries is true`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Success(THOR_COMICS))
        charactersViewModel.run {
            verifyBoolean(isEmptySeries, true)
        }
    }

    @Test
    fun `should verify closeKeyboard`() = runBlocking {
        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyBoolean(closeKeyboard(), true)
        }
    }

    @Test
    fun `should verify if isVisibleClear is false`() = runBlocking {
        charactersViewModel.run {
            onTextChange(THOR.name)
            verifyBoolean(isVisibleClear(), false)
        }
    }

    @Test
    fun `should verify if isVisibleClear is true with null`() = runBlocking {
        charactersViewModel.run {
            onTextChange(null)
            verifyBoolean(isVisibleClear(), true)
        }
    }

    @Test
    fun `should verify if isVisibleClear is true with empty`() = runBlocking {
        charactersViewModel.run {
            onTextChange("")
            verifyBoolean(isVisibleClear(), true)
        }
    }

    @Test
    fun `should clearText`() = runBlocking {
        charactersViewModel.run {
            clearText()
            verifyString(editText(), "")
        }
    }

    @Test
    fun `should verify getCharacter CONNECTION error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.CONNECTION)))

        charactersViewModel.loadCharacter(THOR.name)
        verifyError(CONNECTION)
    }

    @Test
    fun `should verify getCharacter UNAUTHORIZED error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))

        charactersViewModel.loadCharacter(THOR.name)
        verifyError(UNAUTHORIZED)
    }

    @Test
    fun `should verify getCharacter TIME_OUT error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))

        charactersViewModel.loadCharacter(THOR.name)
        verifyError(TIME_OUT)
    }

    @Test
    fun `should verify getCharacter RESPONSE error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))

        charactersViewModel.loadCharacter(THOR.name)
        verifyError(RESPONSE)
    }

    @Test
    fun `should verify getCharacter FAILURE error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))

        charactersViewModel.loadCharacter(THOR.name)
        verifyError(FAILURE)
    }

    @Test
    fun `should verify getComics CONNECTION error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.CONNECTION)))
        verifyError(CONNECTION)
    }

    @Test
    fun `should verify getComics UNAUTHORIZED error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))
        verifyError(UNAUTHORIZED)
    }

    @Test
    fun `should verify getComics TIME_OUT error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))
        verifyError(TIME_OUT)
    }

    @Test
    fun `should verify getComics RESPONSE error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))
        verifyError(RESPONSE)
    }

    @Test
    fun `should verify getComics FAILURE error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getComicsDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))
        verifyError(FAILURE)
    }

    @Test
    fun `should verify getSeries CONNECTION error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.CONNECTION)))
        verifyError(CONNECTION)
    }

    @Test
    fun `should verify getSeries UNAUTHORIZED error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))
        verifyError(UNAUTHORIZED)
    }

    @Test
    fun `should verify getSeries TIME_OUT error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))
        verifyError(TIME_OUT)
    }

    @Test
    fun `should verify getSeries RESPONSE error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))
        verifyError(RESPONSE)
    }

    @Test
    fun `should verify getSeries FAILURE error`() = runBlocking {
        setupComicsAndSeries()

        coEvery { getSeriesDetailUseCase(THOR.id) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))
        verifyError(FAILURE)
    }

    private fun verifyString(liveDataString: LiveData<String?>, valueExpcted: String) {
        charactersViewModel.run {
            liveDataString.run {
                try {
                    observeForever(observerString)
                    assertEquals(value, valueExpcted)
                } finally {
                    removeObserver(observerString)
                }
            }
        }
    }

    private fun verifyBooleanNotNull(liveDataString: LiveData<Boolean>, valueExpcted: Boolean) {
        charactersViewModel.run {
            liveDataString.run {
                try {
                    observeForever(observerBoolean)
                    assertEquals(value, valueExpcted)
                } finally {
                    removeObserver(observerBoolean)
                }
            }
        }
    }

    private fun verifyBoolean(liveDataString: LiveData<Boolean?>, valueExpcted: Boolean) {
        charactersViewModel.run {
            liveDataString.run {
                try {
                    observeForever(observerBoolean)
                    assertEquals(value, valueExpcted)
                } finally {
                    removeObserver(observerBoolean)
                }
            }
        }
    }

    private fun verifyError(valueExpcted: NetworkError) {
        charactersViewModel.run {
            error.run {
                try {
                    observeForever(observerNetworkError)
                    assertEquals(value, valueExpcted)
                } finally {
                    removeObserver(observerNetworkError)
                }
            }
        }
    }

    private fun setupComicsAndSeries() {
        charactersViewModel.run {
            loadCharacter(THOR.name)

            characterName.run {
                try {
                    observeForever(observerString)
                } finally {
                    removeObserver(observerString)
                }
            }
        }
    }
}