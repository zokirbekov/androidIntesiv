package ru.mikhailskiy.intensiv.domain.useCase

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import ru.mikhailskiy.intensiv.data.vo.credit.CreditVo
import ru.mikhailskiy.intensiv.data.vo.movie.MovieDetailVo
import ru.mikhailskiy.intensiv.domain.repository.CreditRepository
import ru.mikhailskiy.intensiv.domain.repository.MovieDetailRepository
import ru.mikhailskiy.intensiv.extension.applySchedulers
import java.util.*

class MovieDetailAndCreditUseCase(
    private val movieDetail: MovieDetailRepository,
    private val creditDetail: CreditRepository
) {
    fun getDetail(movieId: Int) =
        Observable.zip(
            movieDetail.getDetail(movieId),
            creditDetail.getCredits(movieId),
            BiFunction<MovieDetailVo, List<CreditVo>, MovieAndCredit> { movie, credit ->
                MovieAndCredit(movie, credit)
            }
        ).applySchedulers()

    data class MovieAndCredit(
        val movie: MovieDetailVo,
        val credits: List<CreditVo>
    )

    fun updateFavorite(movieId:MovieDetailVo) {

    }
}