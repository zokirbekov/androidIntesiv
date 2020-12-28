package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity
import ru.mikhailskiy.intensiv.data.repository.CategoryRepository
import ru.mikhailskiy.intensiv.data.vo.MovieCategoryVo

object MovieCategoryMapper {
    fun entityToVo(entity: MovieCategoryEntity) =
        MovieCategoryVo(
            id = entity.categoryId,
            nameResource = getNameByType(entity.categoryId)
        )

    fun getNameByType(type: Long)
    = when(type) {
        CategoryRepository.CategoryType.POPULAR -> R.string.popular
        CategoryRepository.CategoryType.NOW_PLAYING -> R.string.now_playing
        CategoryRepository.CategoryType.UPCOMING -> R.string.upcoming
        else -> R.string.movie
    }
}