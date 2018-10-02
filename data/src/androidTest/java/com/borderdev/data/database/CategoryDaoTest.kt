package com.borderdev.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CategoryDaoTest: BaseTestDatabase() {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val numberOfCategories = 10

    @Test
    fun getAllTest() {
        populateCategory(numberOfCategories)

        categoryDao.getAll()
                .test()
                .assertValue {categories ->
                    categories.isNotEmpty()
                }
    }

    @Test
    fun getByIdTest() {
        populateCategory(numberOfCategories)
        val _category = createCategory(0)

        val categoryId = categoryDao.insert(_category)

        categoryDao
                .getById(categoryId)
                .test()
                .assertValue {
                    it.id.equals(categoryId)
                }

    }

    @Test
    fun getByEpisodeTest() {
        val episodeId = 5L
        val _category = createCategory(episodeId)

        categoryDao.insert(_category)

        categoryDao.getByEpisode(episodeId)
                .test()
                .assertValue { categories ->
                    categories.first().episodeId == episodeId
                }
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

        val _category = categoryDao.getAll()
                .blockingFirst().first()

        val categoryId = _category.id

        _category.name = newName

        categoryDao.update(_category)

        categoryDao.getById(categoryId)
                .test()
                .assertValue{ category ->
                    category.name == newName
                }
    }

    @Test
    fun updateListTest() {
        val categoriesId = mutableListOf<Long>()
        val newName = "New Name Test"
        populateCategory(numberOfCategories)

        for (i in 0..2) {
            categoriesId.add(categoryDao.insert(createCategory(0)))
        }

        val _category1 = categoryDao.getById(categoriesId[0]).blockingGet()
        _category1.name = newName
        val _category2 = categoryDao.getById(categoriesId[1]).blockingGet()
        _category2.name = newName
        val _category3 = categoryDao.getById(categoriesId[2]).blockingGet()
        _category3.name = newName

        categoryDao.update(_category1, _category2, _category3)

        val categories = categoryDao.getAll().blockingFirst()

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

        categoryDao.getById(categoryId)
                .test()
                .assertNoValues()

    }

    @Test
    fun deleteAllTest() {
        populateCategory(numberOfCategories)

        categoryDao.deleteAll()

        categoryDao.getAll()
                .test()
                .assertValue {
                    categories-> categories.isEmpty()
                }

    }
}