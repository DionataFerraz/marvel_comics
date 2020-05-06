package com.dionataferraz.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dionataferraz.core.internal.NetworkError
import com.dionataferraz.core.internal.NetworkErrorType
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.interactor.GetCharacterDetailUseCase
import com.dionataferraz.presentation.common.verifyBoolean
import com.dionataferraz.presentation.common.verifyBooleanNotNull
import com.dionataferraz.presentation.common.verifyError
import com.dionataferraz.presentation.common.verifyString
import com.dionataferraz.presentation.data.CharacterData.THOR
import com.dionataferraz.presentation.data.CharacterData.THOR_LIST
import com.dionataferraz.presentation.data.ErrorData.CONNECTION
import com.dionataferraz.presentation.data.ErrorData.FAILURE
import com.dionataferraz.presentation.data.ErrorData.RESPONSE
import com.dionataferraz.presentation.data.ErrorData.TIME_OUT
import com.dionataferraz.presentation.data.ErrorData.UNAUTHORIZED
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase = mockk<GetCharacterDetailUseCase>(relaxed = true)

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        charactersViewModel = CharactersViewModel(useCase)
        coEvery { useCase.invoke(THOR.name) } returns MutableLiveData(Resource.Success(THOR_LIST))
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

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyError(error, CONNECTION)
        }
    }

    @Test
    fun `should verify getCharacter UNAUTHORIZED error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.UNAUTHORIZED)))

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyError(error, UNAUTHORIZED)
        }
    }

    @Test
    fun `should verify getCharacter TIME_OUT error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.TIME_OUT)))

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyError(error, TIME_OUT)
        }
    }

    @Test
    fun `should verify getCharacter RESPONSE error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.RESPONSE)))

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyError(error, RESPONSE)
        }
    }

    @Test
    fun `should verify getCharacter FAILURE error`() = runBlocking {
        coEvery { useCase(THOR.name) } returns MutableLiveData(Resource.Error(NetworkError(NetworkErrorType.FAILURE)))

        charactersViewModel.run {
            loadCharacter(THOR.name)
            verifyError(error, FAILURE)
        }
    }
}