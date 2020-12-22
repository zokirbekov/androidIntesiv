package ru.mikhailskiy.intensiv.mapper

import ru.mikhailskiy.intensiv.data.genre.GenreDto
import ru.mikhailskiy.intensiv.data.genre.GenreEntity

object GenreMapper {
    fun dtoToEntity(genre:GenreDto) = GenreEntity(
        genreId = genre.id ?: 0,
        genreName = genre.name
    )

    fun entityToDto(genreEntity: GenreEntity) = GenreDto(
        id = genreEntity.genreId,
        name = genreEntity.genreName
    )
}