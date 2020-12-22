package ru.mikhailskiy.intensiv.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mikhailskiy.intensiv.data.company.ProductionCompaniesEntity
import ru.mikhailskiy.intensiv.data.country.ProductionCountriesEntity
import ru.mikhailskiy.intensiv.data.genre.GenreEntity
import ru.mikhailskiy.intensiv.data.movie.MovieAndGenreCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieAndProCompanyCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieAndProCountryCrossRef
import ru.mikhailskiy.intensiv.data.movie.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        ProductionCompaniesEntity::class,
        ProductionCountriesEntity::class,
        MovieAndGenreCrossRef::class,
        MovieAndProCountryCrossRef::class,
        MovieAndProCompanyCrossRef::class
    ],
    version = 1
)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance:MovieDatabase? = null

        @Synchronized
        fun get(context: Context): MovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,"movieDatabase"
                ).build()
            }
            return instance!!
        }
    }
}