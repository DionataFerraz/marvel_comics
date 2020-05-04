package com.dionataferraz.domain.interactor

import com.dionataferraz.data.repository.CharacterRepository
import com.dionataferraz.domain.data.CharacterData.THOR
import com.dionataferraz.domain.data.CharacterData.CHARACTER_DETAIL_THOR
import com.dionataferraz.domain.data.CharacterData.COMMON_ITEM_DETAIL_COMICS_THOR
import com.dionataferraz.domain.data.CharacterData.THOR_COMICS
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetComicsDetailUseCaseTest {

    private val repository: CharacterRepository = mockk()

    @Before
    fun init() {
        coEvery { repository.loadComics(THOR.id) } returns THOR_COMICS
    }

    @Test
    fun `should call loadComics on repository`() = runBlocking {
        // Given
        GetComicsDetailUseCase(repository).invoke(THOR.id)
        // When
        coVerify { repository.loadComics(THOR.id) }
        // Then
        confirmVerified(repository)
    }

    @Test
    fun `should call loadComics and validate list size`() = runBlocking {
        // Given
        val useCase = GetComicsDetailUseCase(repository)
        // When
        val comics = useCase.invoke(THOR.id)
        // Then
        assertEquals(comics.size, COMMON_ITEM_DETAIL_COMICS_THOR.size)
    }

    @Test
    fun `should call loadComics and validate data`() = runBlocking {
        // Given
        val useCase = GetComicsDetailUseCase(repository)
        // When
        val comics = useCase.invoke(THOR.id)
        // Then
        assertEquals(COMMON_ITEM_DETAIL_COMICS_THOR, comics)
    }
}
