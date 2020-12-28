package ru.mikhailskiy.intensiv.presentation.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mikhailskiy.intensiv.data.mappers.TvShowMapper
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo
import ru.mikhailskiy.intensiv.domain.useCase.TvShowsUseCase
import timber.log.Timber

class TvShowsViewModel(private val tvShowsUseCase: TvShowsUseCase) : ViewModel() {
    private val _liveTvShows = MutableLiveData<List<TvShowVo>>()
    private val _liveOnProgress = MutableLiveData<Boolean>(false)

    val liveTvShows:LiveData<List<TvShowVo>> = _liveTvShows
    val liveOnProgress:LiveData<Boolean> = _liveOnProgress

    fun getTvShows() = tvShowsUseCase.getTvShows()
        .doOnSubscribe {
            _liveOnProgress.postValue(true)
        }
        .doOnTerminate {
            _liveOnProgress.postValue(false)
        }
        .subscribe(
            {
                _liveTvShows.postValue(it)
            },
            {
                Timber.e(it)
            }
        )
}