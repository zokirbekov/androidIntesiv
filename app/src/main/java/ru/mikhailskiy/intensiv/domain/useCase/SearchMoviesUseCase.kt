package ru.mikhailskiy.intensiv.domain.useCase

import ru.mikhailskiy.intensiv.domain.repository.SearchMoviesRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers

class SearchMoviesUseCase(private val searchMoviesRepository: SearchMoviesRepository) {
    fun searchMovies(text: String, page: Int = 1) =
        searchMoviesRepository.searchAndGetMovies(text, page)
            .applySchedulers()
}