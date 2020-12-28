package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.movie.MovieDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieResponse
import ru.mikhailskiy.intensiv.data.entity.movie.CategoryWithMovie
import ru.mikhailskiy.intensiv.data.entity.movie.MovieEntity
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

object MovieMapper {

    fun dtoToVo(dto: MovieResponse): List<MovieVo>? {
        return dto.results?.map { dtoToVo(it) }
    }

    fun dtoToVo(dto: MovieDto) =
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

    fun dtoToEntity(dto: MovieDto) =
        MovieEntity().apply {
            this.title = dto.title
            this.backdropPath = dto.backdropPath
            this.posterPath = dto.posterPath
            this.movieId = dto.id ?: 0
            this.overview = dto.overview
            this.popularity = dto.popularity
            this.voteAverage = dto.voteAverage
            this.releaseDate = dto.releaseDate
            this.adult = dto.adult
            this.originalLanguage = dto.originalLanguage
            this.voteCount = dto.voteCount
            this.video = dto.video
            this.originalTitle = dto.originalTitle
        }

    fun voToEntity(vo:MovieVo) =
        MovieEntity().apply {
            this.title = vo.title
            this.backdropPath = vo.backdropPath
            this.posterPath = vo.posterPath
            this.movieId = vo.id ?: 0
            this.overview = vo.overview
            this.popularity = vo.popularity
            this.voteAverage = vo.voteAverage
            this.releaseDate = vo.releaseDate
            this.isFavorite = vo.isFavorite
        }

    fun entityToVo(entity: CategoryWithMovie) =
        entity.movies.map {
            entityToVo(it)
        }

    fun entityToVo(entity: MovieEntity) =
        MovieVo().apply {
            this.title = entity.title
            this.backdropPath = entity.backdropPath
            this.posterPath = entity.posterPath
            this.id = entity.movieId
            this.overview = entity.overview
            this.popularity = entity.popularity
            this.voteAverage = entity.voteAverage
            this.releaseDate = entity.releaseDate
            this.isFavorite = entity.isFavorite
        }

}