package ru.mikhailskiy.intensiv

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.mikhailskiy.intensiv.data.db.MovieDatabase
import ru.mikhailskiy.intensiv.data.repository.CategoryRepository
import ru.mikhailskiy.intensiv.domain.di.databaseModule
import ru.mikhailskiy.intensiv.domain.di.movieDetailModule
import ru.mikhailskiy.intensiv.domain.repository.MovieDbRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@MovieFinderApp)
            modules(listOf(movieDetailModule, databaseModule))
        }

        val categoryDao = MovieDatabase.get(this).categoryDao()
        categoryDao.save(CategoryRepository.get())
            .applySchedulers()
            .subscribe()
        initDebugTools()
    }
    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set
    }
}