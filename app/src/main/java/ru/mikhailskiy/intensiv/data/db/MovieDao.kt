package ru.mikhailskiy.intensiv.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.entity.movie.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: MovieEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movie: List<MovieEntity>): Single<List<Long>>

    @Query("SELECT * FROM MovieEntity")
    fun getAll(): Observable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getOnlyFavorites(): Observable<List<MovieAndFavorite>>

    @Query("SELECT COUNT(*) FROM FavoriteMovieEntity WHERE movId = :movieId")
    fun isMovieFavorite(movieId: Long): Single<Int>

    @Query("SELECT COUNT(*) FROM FavoriteMovieEntity")
    fun getCountOfFavorites() : Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(favoriteMovie: FavoriteMovieEntity) : Completable

    @Query("DELETE FROM FavoriteMovieEntity WHERE movId = :movieId")
    fun deleteFavorite(movieId: Long) : Completable

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getMoviesAndGenres(): Observable<List<MovieAndGenresEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getMoviesDetails(): Observable<List<MovieDetailEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieId = :movieId")
    fun getMovieDetailById(movieId: Long): Observable<List<MovieDetailEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieId =:movieId")
    fun getMovieAndGenresByMovieId(movieId: Long): Observable<MovieAndGenresEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndCategoryJoin(join: MovieAndCategoryCrossRefEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndCategoryJoins(join: List<MovieAndCategoryCrossRefEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndGenreJoin(join: MovieAndGenreCrossRefEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCompanyJoin(join: MovieAndProCompanyCrossRefEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCountryJoin(join: MovieAndProCountryCrossRefEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndGenreJoins(join: List<MovieAndGenreCrossRefEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCompanyJoins(join: List<MovieAndProCompanyCrossRefEntity>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCountryJoins(join: List<MovieAndProCountryCrossRefEntity>): Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieEntity): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(movie: List<MovieEntity>): Completable

    @Delete
    fun delete(movie: MovieEntity): Completable

    @Delete
    fun deleteAll(movie: List<MovieEntity>): Completable

}