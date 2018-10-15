package com.borderdev.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.isEmpty
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostEntityDaoTest : BaseTestDatabase() {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val numberOfPosts = 10

    @Test
    fun getAllTest() {
        populatePost(numberOfPosts)

        postDao.getAll()
                .test()
                .assertValue {
                    it.isNotEmpty()
                }

    }

    @Test
    fun getById() {
        val _post = createPost()

        val postId = postDao.insert(_post)

        postDao.getById(postId)
                .test()
                .assertValue {
                    it.id.equals(postId)
                }

    }

    @Test
    fun insertTest() {
        val post = createPost()

        val postId = postDao.insert(post)

        assertNotEquals(0, postId)
    }

    @Test
    fun insertListTest() {
        val insertedPosts = postDao.insert(
                createPost(),
                createPost(),
                createPost()
        )
        assertEquals(3, insertedPosts.size)
    }

    @Test
    fun updateTest() {
        val newTitle = "New Title Test"
        populatePost(numberOfPosts)

        val _posts = postDao.getAll().blockingFirst()

        _posts.first().title = newTitle
        val postId = _posts.first().id

        postDao.update(_posts.first())

        postDao.getById(postId)
                .test()
                .assertValue {
                    it.title == newTitle
                }

    }

    @Test
    fun updateListTest() {
        val postIds = mutableListOf<Long>()
        val newTitle = "New Title Test"
        populatePost(numberOfPosts)

        for (i in 0..2) {
            postIds.add(postDao.insert(createPost()))
        }

        val _post1 = postDao.getById(postIds[0]).blockingGet()
        _post1.title = newTitle
        val _post2 = postDao.getById(postIds[1]).blockingGet()
        _post2.title = newTitle
        val _post3 = postDao.getById(postIds[2]).blockingGet()
        _post3.title = newTitle

        postDao.update(_post1, _post2, _post3)

        val posts = postDao.getAll().blockingFirst()

        for (post in posts) {
            for (postId in postIds) {
                if (post.id == postId) {
                    assertEquals(newTitle, post.title)
                }
            }
        }
    }

    @Test
    fun deleteByIdTest() {
        populatePost(numberOfPosts)

        val _post = createPost()

        val postId = postDao.insert(_post)

        postDao.deleteById(postId)

        postDao.getById(postId)
                .test()
                .assertNoValues()
    }

    @Test
    fun deleteAllTest() {
        populatePost(numberOfPosts)

        postDao.deleteAll()

        postDao.getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }

    }
}