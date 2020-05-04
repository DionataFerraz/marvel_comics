package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.data.CharacterData
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

class GetSeriesDetailUseCaseTest {

    private val repository: CharacterRepository = mockk()

    @Before
    fun init() {
        coEvery { repository.loadSeries(THOR.id) } returns CharacterData.THOR_SERIES
    }

    @Test
    fun `should call loadSeries on repository`() = runBlocking {
        // Given
        GetSeriesDetailUseCase(repository).invoke(THOR.id)
        // When
        coVerify { repository.loadSeries(THOR.id) }
        // Then
        confirmVerified(repository)
    }

    @Test
    fun `should call loadSeries and validate list size`() = runBlocking {
        // Given
        val useCase = GetSeriesDetailUseCase(repository)
        // When
        val comics = useCase.invoke(THOR.id)
        // Then
        assertEquals(comics.size, CharacterData.COMMON_ITEM_DETAIL_SERIES_THOR.size)
    }

    @Test
    fun `should call loadSeries and validate data`() = runBlocking {
        // Given
        val useCase = GetSeriesDetailUseCase(repository)
        // When
        val comics = useCase.invoke(THOR.id)
        // Then
        assertEquals(CharacterData.COMMON_ITEM_DETAIL_SERIES_THOR, comics)
    }
}
