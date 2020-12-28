package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.db.CategoryDao
import ru.mikhailskiy.intensiv.data.db.MovieDao
import ru.mikhailskiy.intensiv.data.entity.movie.MovieAndCategoryCrossRefEntity
import ru.mikhailskiy.intensiv.data.entity.movie.MovieEntity
import ru.mikhailskiy.intensiv.extension.applySchedulers

abstract class MovieDbRepository(private val movieDao: MovieDao) : MoviesRepository{
    fun save(entity: MovieEntity) = movieDao.save(entity)

    fun saveAll(entities: List<MovieEntity>) =
        movieDao.saveAll(entities)

    fun update(entity: MovieEntity) = movieDao.update(entity)

    fun updateAll(entities: List<MovieEntity>) =
        movieDao.updateAll(entities)

    fun delete(entity: MovieEntity) =
        movieDao.delete(entity)

    fun deleteAll(entities: List<MovieEntity>) =
        movieDao.deleteAll(entities)

    abstract fun saveCategoryAndMovie(entity: MovieAndCategoryCrossRefEntity) : Single<Long>

    abstract fun saveCategoriesAndMovies(entity: List<MovieAndCategoryCrossRefEntity>) : Single<List<Long>>
}