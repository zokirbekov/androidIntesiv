package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.GenreDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.vo.GenreVo

object GenreMapper {

    fun dtoToVo(dto: MovieDetailDto) = dto.genre?.map { dtoToVo(it) }

    fun dtoToVo(dto: GenreDto) = GenreVo(
        id = dto.id,
        name = dto.name
    )
}