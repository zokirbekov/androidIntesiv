package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.GenreDto
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDetailDto
import ru.mikhailskiy.intensiv.data.vo.GenreVo
import ru.mikhailskiy.intensiv.data.vo.movie.MovieDetailVo

object MovieDetailMapper {
    fun dtoToVo(dto: MovieDetailDto) = MovieDetailVo(
        genre = GenreMapper.dtoToVo(dto),
        productionCompanies = ProductionCompaniesMapper.dtoToVo(dto),
        productionCountries = ProductionCountriesMapper.dtoToVo(dto)
    ).apply {
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