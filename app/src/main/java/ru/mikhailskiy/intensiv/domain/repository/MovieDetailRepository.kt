package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.vo.movie.MovieDetailVo

interface MovieDetailRepository {
    fun getDetail(movieId:Int) : Single<MovieDetailVo>
}