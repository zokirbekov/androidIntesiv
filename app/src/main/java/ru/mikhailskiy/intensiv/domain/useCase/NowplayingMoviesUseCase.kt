package ru.mikhailskiy.intensiv.domain.useCase

import ru.mikhailskiy.intensiv.domain.repository.MoviesRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class NowplayingMoviesUseCase(private val repository: MoviesRepository) {
    fun getMovies(page:Int) = repository.getMovies(page).applySchedulers()
}