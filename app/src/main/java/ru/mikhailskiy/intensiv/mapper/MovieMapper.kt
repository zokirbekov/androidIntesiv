package ru.mikhailskiy.intensiv.mapper

import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.data.movie.MovieEntity

object MovieMapper {
    fun dtoToEntity(movieDto: MovieDto) = MovieEntity().apply {
        this.adult = movieDto.adult
        this.backdropPath = movieDto.backdropPath
        this.movieId = movieDto.id ?: 0
        this.originalLanguage = movieDto.originalLanguage
        this.originalTitle = movieDto.originalTitle
        this.voteCount = movieDto.voteCount
        this.voteAverage = movieDto.voteAverage
        this.video = movieDto.video
        this.title = movieDto.title
        this.releaseDate = movieDto.releaseDate
        this.posterPath = movieDto.posterPath
        this.popularity = movieDto.popularity
        this.overview = movieDto.overview
    }

    fun entityToDto(movieEntity: MovieEntity) = MovieDto().apply {
        this.adult = movieEntity.adult
        this.backdropPath = movieEntity.backdropPath
        this.id = movieEntity.movieId
        this.originalLanguage = movieEntity.originalLanguage
        this.originalTitle = movieEntity.originalTitle
        this.voteCount = movieEntity.voteCount
        this.voteAverage = movieEntity.voteAverage
        this.video = movieEntity.video
        this.title = movieEntity.title
        this.releaseDate = movieEntity.releaseDate
        this.posterPath = movieEntity.posterPath
        this.popularity = movieEntity.popularity
        this.overview = movieEntity.overview
    }
}