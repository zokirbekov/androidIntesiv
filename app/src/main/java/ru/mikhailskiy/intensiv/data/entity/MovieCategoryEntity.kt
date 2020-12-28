package ru.mikhailskiy.intensiv.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryId:Long
)