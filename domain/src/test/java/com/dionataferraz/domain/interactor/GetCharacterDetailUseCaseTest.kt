package com.dionataferraz.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.data.CharacterThorData.CHARACTER_DETAIL_THOR_LIST
import com.dionataferraz.domain.data.CharacterThorData.THOR
import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCharacterDetailUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: CharacterRepository = mockk()
    private val observerCharacter = Observer<Resource<List<CharacterDetail?>>> {}

    @Before
    fun init() {
        coEvery { repository.loadCharacter(THOR.name) } returns MutableLiveData(Resource.Success(CHARACTER_DETAIL_THOR_LIST))
    }

    @Test
    fun `should call loadCharacter on repository`() = runBlocking {
        // Given
        GetCharacterDetailUseCase(repository).invoke(THOR.name)
        // When
        coVerify { repository.loadCharacter(THOR.name) }
        // Then
        confirmVerified(repository)
    }

    @Test
    fun `should call loadCharacter and validate data`() = runBlocking {
        val character = repository.loadCharacter(THOR.name)

        character.run {
            try {
                observeForever(observerCharacter)
                assertEquals(value?.data, CHARACTER_DETAIL_THOR_LIST)
            } finally {
                removeObserver(observerCharacter)
            }
        }
    }

}
