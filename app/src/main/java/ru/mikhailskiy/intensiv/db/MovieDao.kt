package ru.mikhailskiy.intensiv.db

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.movie.*
import ru.mikhailskiy.intensiv.data.movie.detail.MovieDetail

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie:MovieEntity) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movie:List<MovieEntity>) : Single<List<Long>>

    @Query("SELECT * FROM MovieEntity")
    fun getAll() : Observable<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE isFavorite=1")
    fun getOnlyFavorites() : Observable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getMoviesAndGenres() : Observable<List<MovieAndGenres>>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getMoviesDetails() : Observable<List<MovieDetail>>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieId = :movieId")
    fun getMovieDetailById(movieId:Long) : Observable<List<MovieDetail>>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieId =:movieId")
    fun getMovieAndGenresByMovieId(movieId:Long) : Observable<MovieAndGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndGenreJoin(join:MovieAndGenreCrossRef) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCompanyJoin(join:MovieAndProCompanyCrossRef) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCountryJoin(join:MovieAndProCountryCrossRef) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndGenreJoins(join:List<MovieAndGenreCrossRef>) : Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCompanyJoins(join:List<MovieAndProCompanyCrossRef>) : Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieAndProCountryJoins(join:List<MovieAndProCountryCrossRef>) : Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie:MovieEntity) : Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(movie:List<MovieEntity>) : Single<Int>

}