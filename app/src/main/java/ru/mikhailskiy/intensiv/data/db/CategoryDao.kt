package ru.mikhailskiy.intensiv.data.db

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity
import ru.mikhailskiy.intensiv.data.entity.movie.CategoryWithMovie
import ru.mikhailskiy.intensiv.data.entity.movie.MovieWithCategories

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(category:MovieCategoryEntity) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(category:List<MovieCategoryEntity>) : Single<List<Long>>

    @Query("SELECT * FROM MovieCategoryEntity")
    fun get() : Observable<List<MovieCategoryEntity>>

    @Transaction
    @Query("SELECT * FROM MovieCategoryEntity")
    fun getCategoryWithMovies() : Observable<List<CategoryWithMovie>>

    @Transaction
    @Query("SELECT * FROM MovieCategoryEntity WHERE categoryId = :categoryId")
    fun getMoviesByCategory(categoryId:Long) : Observable<CategoryWithMovie>
}