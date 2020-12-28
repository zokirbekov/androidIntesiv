package ru.mikhailskiy.intensiv.data.repository

import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity

object CategoryRepository {
    fun get() = arrayListOf(
        MovieCategoryEntity(CategoryType.UPCOMING),
        MovieCategoryEntity(CategoryType.NOW_PLAYING),
        MovieCategoryEntity(CategoryType.POPULAR)
    )

    class CategoryType {
        companion object {
            const val UPCOMING = 1L
            const val NOW_PLAYING = 2L
            const val POPULAR = 3L
        }
    }
}