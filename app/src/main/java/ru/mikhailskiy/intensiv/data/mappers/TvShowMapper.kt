package ru.mikhailskiy.intensiv.data.mappers

import ru.mikhailskiy.intensiv.data.dto.tvShow.TvShowDto
import ru.mikhailskiy.intensiv.data.dto.tvShow.TvShowResponse
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo

object TvShowMapper {
    fun dtoToVo(dto: TvShowDto) = TvShowVo(
        name = dto.name
    ).apply {
        this.name = dto.name
        this.voteAverage = dto.voteAverage
        this.popularity = dto.popularity
        this.overview = dto.overview
        this.id = dto.id
        this.backdropPath = dto.backdropPath
        this.posterPath = dto.posterPath
    }

    fun dtoToVo(dto: TvShowResponse) = dto.results?.map { dtoToVo(it) }
}