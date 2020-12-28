package ru.mikhailskiy.intensiv.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mikhailskiy.intensiv.data.entity.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.entity.ProductionCountriesEntity
import ru.mikhailskiy.intensiv.data.entity.GenreEntity
import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity
import ru.mikhailskiy.intensiv.data.entity.movie.*

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        ProductionCompaniesEntity::class,
        ProductionCountriesEntity::class,
        MovieAndGenreCrossRefEntity::class,
        MovieAndProCountryCrossRefEntity::class,
        MovieAndProCompanyCrossRefEntity::class,
        MovieCategoryEntity::class,
        MovieAndCategoryCrossRefEntity::class,
        FavoriteMovieEntity::class
    ],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private var instance: MovieDatabase? = null

        @Synchronized
        fun get(context: Context): MovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java, "movieDatabase"
                ).build()
            }
            return instance!!
        }
    }
}