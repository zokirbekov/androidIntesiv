package ru.mikhailskiy.intensiv.presentation.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mikhailskiy.intensiv.data.Response
import ru.mikhailskiy.intensiv.data.mappers.TvShowMapper
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo
import ru.mikhailskiy.intensiv.domain.useCase.TvShowsUseCase
import timber.log.Timber

class TvShowsViewModel(private val tvShowsUseCase: TvShowsUseCase) : ViewModel() {
    private val _liveTvShows = MutableLiveData<List<TvShowVo>>()
    private val _liveOnProgress = MutableLiveData<Boolean>(false)

    val liveTvShows:LiveData<List<TvShowVo>> = _liveTvShows
    val liveOnProgress:LiveData<Boolean> = _liveOnProgress

    fun getTvShows() {
        _liveOnProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = tvShowsUseCase.getTvShows()

            if (response is Response.Success<*>)
                _liveTvShows.postValue(response.data as List<TvShowVo>)

            _liveOnProgress.postValue(false)
        }
    }
}