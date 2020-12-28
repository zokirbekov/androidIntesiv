package ru.mikhailskiy.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.mikhailskiy.intensiv.data.dto.credit.CreditDto
import ru.mikhailskiy.intensiv.data.vo.credit.CreditVo

interface CreditRepository {
    fun getCredits(movieId:Int) : Observable<List<CreditVo>>
}