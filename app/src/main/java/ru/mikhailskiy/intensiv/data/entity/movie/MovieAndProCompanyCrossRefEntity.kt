package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "proCompanyId"])
data class MovieAndProCompanyCrossRefEntity(
    val movieId:Long,
    val proCompanyId:Long
)