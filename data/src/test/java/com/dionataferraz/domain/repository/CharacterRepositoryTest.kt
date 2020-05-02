package com.dionataferraz.domain.repository

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.data.CharacterData.IRON_MAN
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    private val repository: CharacterRepository = mockk()

    @Before
    fun init() {
        coEvery { repository.loadCharacter(IRON_MAN.name) } returns IRON_MAN
    }

    @Test
    fun `should call loadCharacter and validate data from data`() = runBlocking {
        val character = repository.loadCharacter(IRON_MAN.name)
        assertEquals(IRON_MAN, character)
    }
}
