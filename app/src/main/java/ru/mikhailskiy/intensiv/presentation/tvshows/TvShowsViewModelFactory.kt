package ru.mikhailskiy.intensiv.presentation.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mikhailskiy.intensiv.domain.useCase.TvShowsUseCase

class TvShowsViewModelFactory(private val tvShowsUseCase: TvShowsUseCase) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowsViewModel::class.java)) {
            return TvShowsViewModel(tvShowsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}