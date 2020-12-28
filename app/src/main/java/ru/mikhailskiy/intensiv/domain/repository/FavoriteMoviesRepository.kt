package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo

interface FavoriteMoviesRepository : MoviesRepository {
    fun saveFavorite(movieVo: MovieVo) : Completable
    fun deleteFavorite(movieVo: MovieVo) : Completable
    fun isFavorite(movieId:Long) : Single<Boolean>
    fun getCountOfFavorites() : Single<Int>
}