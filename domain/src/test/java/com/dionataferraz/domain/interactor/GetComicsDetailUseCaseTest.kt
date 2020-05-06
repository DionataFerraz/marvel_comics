package com.dionataferraz.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.data.CharacterThorData.COMMON_ITEM_DETAIL_COMICS_THOR
import com.dionataferraz.domain.data.CharacterThorData.THOR
import com.dionataferraz.domain.model.CommonItemDetail
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

class GetComicsDetailUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: CharacterRepository = mockk()
    private val observerCommonItem = Observer<Resource<List<CommonItemDetail?>>> {}

    @Before
    fun init() {
        coEvery { repository.loadComics(THOR.id) } returns MutableLiveData(Resource.Success(COMMON_ITEM_DETAIL_COMICS_THOR))
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
        assertEquals(comics.value?.data?.size, COMMON_ITEM_DETAIL_COMICS_THOR.size)
    }

    @Test
    fun `should call loadComics and validate data`() = runBlocking {
        val character = repository.loadComics(THOR.id)

        character.run {
            try {
                observeForever(observerCommonItem)
                assertEquals(value?.data, COMMON_ITEM_DETAIL_COMICS_THOR)
            } finally {
                removeObserver(observerCommonItem)
            }
        }
    }
}
