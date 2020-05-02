package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.data.CharacterData.THOR
import com.dionataferraz.domain.data.CharacterData.CHARACTER_DETAIL_THOR
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterDetailUseCaseTest {

    private val repository: CharacterRepository = mockk()

    @Before
    fun init() {
        coEvery { repository.loadCharacter(THOR.name) } returns THOR
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
        // Given
        val useCase = GetCharacterDetailUseCase(repository)
        // When
        val character = useCase.invoke(THOR.name)
        // Then
        assertEquals(CHARACTER_DETAIL_THOR, character)
    }
}
