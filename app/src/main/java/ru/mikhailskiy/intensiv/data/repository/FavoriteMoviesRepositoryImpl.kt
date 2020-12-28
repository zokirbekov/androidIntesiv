package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.db.MovieDao
import ru.mikhailskiy.intensiv.data.entity.movie.FavoriteMovieEntity
import ru.mikhailskiy.intensiv.data.mappers.MovieDetailMapper
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.FavoriteMoviesRepository
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository

class FavoriteMoviesRepositoryImpl(private val movieDao: MovieDao) : FavoriteMoviesRepository {

    override fun getMovies(page: Int) = movieDao
        .getOnlyFavorites()
        .map { movies ->
            movies.filter { it.favorite != null }
                .map { movie ->
                    MovieMapper.entityToVo(movie.movie)
                }
        }

    override fun saveFavorite(movieVo: MovieVo) =
        movieDao.saveFavorite(FavoriteMovieEntity(movieVo.id))

    override fun deleteFavorite(movieVo: MovieVo) =
        movieDao.deleteFavorite(movieVo.id ?: 0)

    override fun isFavorite(movieId: Long) = movieDao.isMovieFavorite(movieId).map {
        it != 0
    }

    override fun getCountOfFavorites() = movieDao.getCountOfFavorites()
}