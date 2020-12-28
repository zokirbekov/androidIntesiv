package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.MovieDetailMapper
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.movie.MovieDetailVo
import ru.mikhailskiy.intensiv.domain.repository.MovieDetailRepository
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository

class MovieDetailRemoteRepository : MovieDetailRepository {
    override fun getDetail(movieId:Int): Observable<MovieDetailVo> =
        MovieApiClient.api.getDetail(movieId)
            .map {
                MovieDetailMapper.dtoToVo(it)
            }
}