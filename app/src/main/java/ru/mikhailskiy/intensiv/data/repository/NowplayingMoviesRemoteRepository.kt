package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository

class NowplayingMoviesRemoteRepository : MoviesRepository {
    override fun getMovies(page: Int): Observable<List<MovieVo>> =
        MovieApiClient.api.getNowPlaying(page).map {
            MovieMapper.dtoToVo(it)
        }
}