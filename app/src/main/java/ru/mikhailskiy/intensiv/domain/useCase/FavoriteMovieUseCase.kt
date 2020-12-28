package ru.mikhailskiy.intensiv.domain.useCase

import io.reactivex.Completable
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.FavoriteMoviesRepository
import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository.Companion.DEFAULT_PAGE
import ru.mikhailskiy.intensiv.extension.applySchedulers

class FavoriteMovieUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepository) {
    fun update(movieVo: MovieVo): Completable {
        movieVo.apply {
            isFavorite = !isFavorite
        }
        return (if (movieVo.isFavorite) {
            favoriteMoviesRepository.saveFavorite(movieVo)
        } else
            favoriteMoviesRepository.deleteFavorite(movieVo)).applySchedulers()
    }

    fun getFavoriteMovies() = favoriteMoviesRepository.getMovies(DEFAULT_PAGE)
        .applySchedulers()

    fun isFavorite(movieId: Long) = favoriteMoviesRepository.isFavorite(movieId)
        .applySchedulers()

    fun getCountOfFacorites() = favoriteMoviesRepository.getCountOfFavorites()
        .applySchedulers()
}