package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.movie.MovieDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieResponse
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

object MovieMapper {

    fun dtoToVo(dto : MovieResponse) : List<MovieVo>? {
        return dto.results?.map { dtoToVo(it) }
    }

    fun dtoToVo(dto:MovieDto) =
        MovieVo().apply {
            this.title = dto.title
            this.backdropPath = dto.backdropPath
            this.posterPath = dto.posterPath
            this.id = dto.id
            this.overview = dto.overview
            this.popularity = dto.popularity
            this.voteAverage = dto.voteAverage
            this.releaseDate = dto.releaseDate
        }
}