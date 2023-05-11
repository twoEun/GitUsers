package com.kkc.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kkc.localdata.BookMarkDatabase
import com.kkc.localdata.dataSource.BookMarkDataSource
import com.kkc.localdata.roomData.BookMark
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BookMarkDataBaseTest {
    private lateinit var database: com.kkc.localdata.BookMarkDatabase
    private lateinit var bookmarkDao: com.kkc.localdata.dataSource.BookMarkDataSource

    @Before
    fun dbInitialize() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, com.kkc.localdata.BookMarkDatabase::class.java).build()
        bookmarkDao = database.bookmarkDataSource()
    }

    @After
    fun closeDataBase() {
        database.close()
    }

    @Test
    fun testAddBookMark() {
        val willAddBookMark = com.kkc.localdata.roomData.BookMark(
            index = 0,
            userIdx = 1,
            category = "shop"
        )

        bookmarkDao.addBookMark(willAddBookMark)
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    // 테스트 코드 미완성
    /*@Test
    fun getAllBookMark() {
        bookmarkDao.getAllBookMarks()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValueCount(1)
    }

    @Test
    fun testCategoryBookMark() {
        val savedBookMark = BookMark(
            index = 0,
            userIdx = 1,
            category = "shop"
        )

        val category1 = "shop"
        val category2 = "like"

        bookmarkDao.getCategoryBookMarks(category1)
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertResult(listOf(savedBookMark))

        bookmarkDao.getCategoryBookMarks(category2)
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertResult(emptyList())
    }*/
}
