package com.dionataferraz.domain.repository

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.data.CharacterData.IRON_MAN
import com.dionataferraz.domain.data.CharacterData.IRON_MAN_COMICS
import com.dionataferraz.domain.data.CharacterData.IRON_MAN_SERIES
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    private val repository: CharacterRepository = mockk()

    @Test
    fun `should call loadCharacter and validate data from data`() = runBlocking {
        coEvery { repository.loadCharacter(IRON_MAN.name) } returns IRON_MAN

        val character = repository.loadCharacter(IRON_MAN.name)
        assertEquals(IRON_MAN, character)
    }

    @Test
    fun `should call loadComics and validate data from data`() = runBlocking {
        coEvery { repository.loadComics(IRON_MAN.id) } returns IRON_MAN_COMICS

        val comics = repository.loadComics(IRON_MAN.id)
        assertEquals(IRON_MAN_COMICS, comics)
    }

    @Test
    fun `should call loadSeries and validate data from data`() = runBlocking {
        coEvery { repository.loadSeries(IRON_MAN.id) } returns IRON_MAN_SERIES

        val series = repository.loadSeries(IRON_MAN.id)
        assertEquals(IRON_MAN_SERIES, series)
    }
}
