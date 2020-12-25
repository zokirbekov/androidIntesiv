package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository

class PopularMoviesRemoteRepository : MoviesRepository {
    override fun getMovies(page:Int): Single<List<MovieVo>> =
        MovieApiClient.api.getPopular(page)
            .map {
                MovieMapper.dtoToVo(it)
            }
}