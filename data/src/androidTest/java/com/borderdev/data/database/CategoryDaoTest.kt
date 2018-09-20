package com.borderdev.data.database

import android.support.test.runner.AndroidJUnit4
import com.natpryce.hamkrest.isEmpty
import com.natpryce.hamkrest.assertion.assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest: BaseTestDatabase() {
    val numberOfCategories = 10

    @Test
    fun getAllTest() {
        populateCategory(numberOfCategories)

        val categories = categoryDao.getAll()

        assertTrue(categories.isNotEmpty())
    }

    @Test
    fun getByIdTest() {
        populateCategory(numberOfCategories)
        val _category = createCategory(0)

        val categoryId = categoryDao.insert(_category)

        val category = categoryDao.getById(categoryId)

        assertEquals(categoryId, category.id)

    }

    @Test
    fun getByEpisodeTest() {
        val episodeId = 5L
        val _category = createCategory(episodeId)

        categoryDao.insert(_category)

        val category = categoryDao.getByEpisode(episodeId)

        assertEquals(episodeId, category.first().episodeId)
    }

    @Test
    fun insertTest() {
        val category = createCategory(0)

        val categoryId = categoryDao.insert(category)

        assertNotEquals(0, categoryId)
    }

    @Test
    fun insertListTest() {
        val insertedCategories = categoryDao.insert(
                createCategory(0),
                createCategory(0),
                createCategory(0)
        )

        assertEquals(3, insertedCategories.size)
    }

    @Test
    fun updateTest() {
        val newName = "New Name Test"
        populateCategory(numberOfCategories)

        val _categories = categoryDao.getAll()

        _categories.first().name = newName
        val categoryId = _categories.first().id

        categoryDao.update(_categories.first())

        val category = categoryDao.getById(categoryId)

        assertEquals(newName, category.name)
    }

    @Test
    fun updateListTest() {
        val categoriesId = mutableListOf<Long>()
        val newName = "New Name Test"
        populateCategory(numberOfCategories)

        for (i in 0..2) {
            categoriesId.add(categoryDao.insert(createCategory(0)))
        }

        val _category1 = categoryDao.getById(categoriesId[0])
        _category1.name = newName
        val _category2 = categoryDao.getById(categoriesId[1])
        _category2.name = newName
        val _category3 = categoryDao.getById(categoriesId[2])
        _category3.name = newName

        categoryDao.update(_category1, _category2, _category3)

        val categories = categoryDao.getAll()

        for (category in categories) {
            for (categoryId in categoriesId) {
                if (category.id == categoryId) {
                    assertEquals(newName, category.name)
                }
            }
        }

    }

    @Test
    fun deleteByIdTest() {
        val _category = createCategory(0)

        val categoryId = categoryDao.insert(_category)

        categoryDao.deleteById(categoryId)

        val categories = categoryDao.getAll()

        for (category in categories) {
            assertNotEquals(categoryId, category.id)
        }
    }

    @Test
    fun deleteAllTest() {
        populateCategory(numberOfCategories)

        categoryDao.deleteAll()

        val categories = categoryDao.getAll()

        assert.that(categories, isEmpty)
    }
}