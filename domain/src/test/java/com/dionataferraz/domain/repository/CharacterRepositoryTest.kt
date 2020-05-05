package com.dionataferraz.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dionataferraz.core.internal.Resource
import com.dionataferraz.domain.data.CharacterIronManData.CHARACTER_DETAIL_IRON_MAN
import com.dionataferraz.domain.data.CharacterIronManData.COMMON_ITEM_DETAIL_COMICS_IRON_MAN
import com.dionataferraz.domain.data.CharacterIronManData.COMMON_ITEM_DETAIL_SERIES_IRON_MAN
import com.dionataferraz.domain.data.CharacterIronManData.IRON_MAN
import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.model.CommonItemDetail
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class CharacterRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: CharacterRepository = mockk()
    private val observerCharacter = Observer<Resource<CharacterDetail?>> {}
    private val observerCommonItem = Observer<Resource<List<CommonItemDetail?>>> {}

    @Test
    fun `should call loadCharacter and validate data`() = runBlocking {
        coEvery { repository.loadCharacter(IRON_MAN.name) } returns MutableLiveData(Resource.Success(CHARACTER_DETAIL_IRON_MAN))
        val character = repository.loadCharacter(IRON_MAN.name)

        character.run {
            try {
                observeForever(observerCharacter)
                assertEquals(value?.data, CHARACTER_DETAIL_IRON_MAN)
            } finally {
                removeObserver(observerCharacter)
            }
        }
    }

    @Test
    fun `should call loadComics and validate data`() = runBlocking {
        coEvery { repository.loadComics(IRON_MAN.id) } returns MutableLiveData(Resource.Success(COMMON_ITEM_DETAIL_COMICS_IRON_MAN))
        val character = repository.loadComics(IRON_MAN.id)

        character.run {
            try {
                observeForever(observerCommonItem)
                assertEquals(value?.data, COMMON_ITEM_DETAIL_COMICS_IRON_MAN)
            } finally {
                removeObserver(observerCommonItem)
            }
        }
    }

    @Test
    fun `should call loadSeries and validate data`() = runBlocking {
        coEvery { repository.loadSeries(IRON_MAN.id) } returns MutableLiveData(Resource.Success(COMMON_ITEM_DETAIL_SERIES_IRON_MAN))
        val character = repository.loadSeries(IRON_MAN.id)

        character.run {
            try {
                observeForever(observerCommonItem)
                assertEquals(value?.data, COMMON_ITEM_DETAIL_SERIES_IRON_MAN)
            } finally {
                removeObserver(observerCommonItem)
            }
        }
    }
}
