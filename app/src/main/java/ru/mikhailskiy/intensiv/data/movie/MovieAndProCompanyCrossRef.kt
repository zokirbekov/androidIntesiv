package ru.mikhailskiy.intensiv.data.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "proCompanyId"])
data class MovieAndProCompanyCrossRef(
    val movieId:Long,
    val proCompanyId:Long
)