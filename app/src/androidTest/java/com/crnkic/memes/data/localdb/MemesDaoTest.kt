package com.crnkic.memes.data.localdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MemesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MemesRoomDatabase
    private lateinit var dao: MemesContainerDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MemesRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getMemesContainerDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMemesItem() = runBlockingTest {
        val memesItem = Memes("1", "memes", "url", 2,3,4)
        dao.insertMemeItem(memesItem)

        val allMemesItems = dao.observeAllMemesItems().getOrAwaitValue()
        assertThat(allMemesItems).contains(memesItem)
    }

    @Test
    fun deleteMemeItem() = runBlockingTest {
        val memesItem = Memes("1", "memes", "url", 2,3,4)
        dao.insertMemeItem(memesItem)
        dao.deleteMemeItem(memesItem)

        val allMemesItems = dao.observeAllMemesItems().getOrAwaitValue()
        assertThat(allMemesItems).doesNotContain(memesItem)
    }
}