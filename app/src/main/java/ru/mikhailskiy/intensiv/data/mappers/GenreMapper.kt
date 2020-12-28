package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.GenreDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.entity.GenreEntity
import ru.mikhailskiy.intensiv.data.vo.GenreVo

object GenreMapper {

    fun dtoToVo(dto: MovieDetailDto) = dto.genre?.map { dtoToVo(it) }

    fun dtoToVo(dto: GenreDto) = GenreVo(
        id = dto.id,
        name = dto.name
    )

    fun entityToVo(entity:GenreEntity) = GenreVo(
        id = entity.genreId,
        name = entity.genreName
    )

    fun dtoToEntity(dto:MovieDetailDto) = dto.genre?.map{ dtoToEntity(it) }

    fun dtoToEntity(dto:GenreDto) = GenreEntity(
        genreId = dto.id ?: 0,
        genreName = dto.name
    )
}