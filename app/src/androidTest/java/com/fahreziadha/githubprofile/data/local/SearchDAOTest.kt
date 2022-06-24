package com.fahreziadha.githubprofile.data.local

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fahreziadha.githubprofile.data.local.data_source.SearchDao
import com.fahreziadha.githubprofile.data.local.data_source.SearchDatabase
import com.fahreziadha.githubprofile.domain.model.CacheUser
import kotlinx.coroutines.flow.onEach
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CountDownLatch


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SearchDAOTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: SearchDatabase
    private lateinit var dao: SearchDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SearchDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.searchDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `insertSearchItem_success`() = runBlocking {
        val searchItem =
            CacheUser(1, "test name", "login", "test", "test", "test", "test", "test", 1)
        dao.insertUser(searchItem)
        val latch = CountDownLatch(1)

        val job = launch(Dispatchers.IO) {
            dao.getUsers().collect {
                assertThat(it).contains(searchItem)
                latch.countDown()
            }
        }
        latch.await()
        job.cancel()

    }

    @Test
    fun `insertSearchItem_fail`() = runBlocking {
        val searchItem =
            CacheUser(1, "test name", "login", "test", "test", "test", "test", "test", 1)
        val wrongSearchItem =
            CacheUser(
                2,
                "wrong name",
                "wrong login",
                "wrong test",
                "wrong test",
                "wrong test",
                "wrong test",
                "wrong test",
                1
            )
        dao.insertUser(searchItem)

        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            dao.getUsers().collect {
                assertThat(it).doesNotContain(wrongSearchItem)
                latch.countDown()
            }
        }
        latch.await()
        job.cancel()

    }


    @Test
    fun `deleteAllSearch_success`() = runBlockingTest {
        val searchItem =
            CacheUser(1, "test name", "login", "test", "test", "test", "test", "test", 1)
        dao.insertUser(searchItem)
        dao.deleteAllUser()

        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            dao.getUsers().collect {
                assertThat(it).doesNotContain(searchItem)
                latch.countDown()
            }
        }
        latch.await()
        job.cancel()
    }


    @Test
    fun observeTotalUserSum() = runBlockingTest {
        val searchItem1 =
            CacheUser(1, "test name", "login", "test", "test", "test", "test", "test", 1)
         val searchItem2 =
            CacheUser(2, "test name", "login", "test", "test", "test", "test", "test", 1)
         val searchItem3 =
            CacheUser(3, "test name", "login", "test", "test", "test", "test", "test", 1)
        dao.insertUser(searchItem1)
        dao.insertUser(searchItem2)
        dao.insertUser(searchItem3)

        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            dao.getUsers().collect {
                assertThat(it).hasSize(3)
                latch.countDown()
            }
        }
        latch.await()
        job.cancel()

    }

}