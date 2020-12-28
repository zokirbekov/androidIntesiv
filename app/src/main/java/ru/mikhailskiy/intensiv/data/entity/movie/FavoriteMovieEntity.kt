package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity(
    val movId:Long?
) {
    @PrimaryKey(autoGenerate = true)
    var favoriteId:Int = 0
}