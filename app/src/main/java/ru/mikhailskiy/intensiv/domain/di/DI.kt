package ru.mikhailskiy.intensiv.domain.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.mikhailskiy.intensiv.data.db.MovieDatabase
import ru.mikhailskiy.intensiv.data.repository.CreditRemoteRepository
import ru.mikhailskiy.intensiv.data.repository.MovieDetailRemoteRepository

val movieDetailModule = module {
    single { MovieDetailRemoteRepository() }
    single { CreditRemoteRepository() }
}

val databaseModule = module {
    fun provideDatabase(context: Context) : MovieDatabase {
        return MovieDatabase.get(context)
    }

    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

    single { provideDatabase(androidContext()) }
    single { provideMovieDao(get()) }
}