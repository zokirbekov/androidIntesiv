package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo

interface TvShowsRepository {
    fun getTvShows(page:Int = 1) : Single<List<TvShowVo>>
}