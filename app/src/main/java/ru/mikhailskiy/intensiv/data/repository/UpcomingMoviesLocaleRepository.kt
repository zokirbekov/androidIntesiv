package ru.mikhailskiy.intensiv.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.db.CategoryDao
import ru.mikhailskiy.intensiv.data.db.MovieDao
import ru.mikhailskiy.intensiv.data.entity.movie.MovieAndCategoryCrossRefEntity
import ru.mikhailskiy.intensiv.data.entity.movie.MovieEntity
import ru.mikhailskiy.intensiv.data.mappers.MovieMapper
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.repository.MovieDbRepository

class UpcomingMoviesLocaleRepository(
    private val movieDao: MovieDao,
    private val categoryDao: CategoryDao
) : MovieDbRepository(movieDao) {
    override fun saveCategoryAndMovie(entity: MovieAndCategoryCrossRefEntity): Single<Long> {
        return movieDao.saveMovieAndCategoryJoin(entity)
    }

    override fun saveCategoriesAndMovies(entity: List<MovieAndCategoryCrossRefEntity>): Single<List<Long>> {
        return movieDao.saveMovieAndCategoryJoins(entity)
    }

    override fun getMovies(page: Int): Observable<List<MovieVo>> =
        categoryDao.getMoviesByCategory(CategoryRepository.CategoryType.UPCOMING)
            .map {
                MovieMapper.entityToVo(it)
            }
}