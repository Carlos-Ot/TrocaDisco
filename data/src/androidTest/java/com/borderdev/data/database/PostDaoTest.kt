package com.borderdev.data.database

import androidx.test.runner.AndroidJUnit4
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.isEmpty
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest : BaseTestDatabase() {

    val numberOfPosts = 10

    @Test
    fun getAllTest() {
        populatePost(numberOfPosts)

        val posts = postDao.getAll()

        assertTrue(posts.isNotEmpty())
    }

    @Test
    fun getById() {
        val _post = createPost()

        val postId = postDao.insert(_post)

        val post = postDao.getById(postId)

        assertEquals(postId, post.id)
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

        val _posts = postDao.getAll()

        _posts.first().title = newTitle
        val episodeId = _posts.first().id

        postDao.update(_posts.first())

        val post = postDao.getById(episodeId)

        assertEquals(newTitle, post.title)
    }

    @Test
    fun updateListTest() {
        val postIds = mutableListOf<Long>()
        val newTitle = "New Title Test"
        populatePost(numberOfPosts)

        for (i in 0..2) {
            postIds.add(postDao.insert(createPost()))
        }

        val _post1 = postDao.getById(postIds[0])
        _post1.title = newTitle
        val _post2 = postDao.getById(postIds[1])
        _post2.title = newTitle
        val _post3 = postDao.getById(postIds[2])
        _post3.title = newTitle

        postDao.update(_post1, _post2, _post3)

        val posts = postDao.getAll()

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

        val posts = postDao.getAll()

        for (post in posts) {
            assertNotEquals(postId, post.id)
        }
    }

    @Test
    fun deleteAllTest() {
        populatePost(numberOfPosts)

        postDao.deleteAll()

        val posts = postDao.getAll()

        assert.that(posts, isEmpty)
    }
}