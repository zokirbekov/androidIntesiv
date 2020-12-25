package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository
import ru.mikhailskiy.intensiv.domain.repository.SearchMoviesRepository

class SearchMoviesRemoteRepository : SearchMoviesRepository{
    override fun searchAndGetMovies(text: String, page: Int) : Single<List<MovieVo>> =
        MovieApiClient.api.search(text,page)
            .map(MovieMapper::dtoToVo)

}