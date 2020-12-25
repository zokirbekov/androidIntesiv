package ru.mikhailskiy.intensiv.data.vo.movie

import com.google.gson.annotations.SerializedName
import ru.mikhailskiy.intensiv.data.vo.BaseMovieVo

open class MovieVo : BaseMovieVo() {
    var title: String? = null
    var releaseDate:String? = null
}