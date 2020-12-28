package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity

data class MovieWithCategories(
    @Embedded val movie:MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "categoryId",
        associateBy = Junction(MovieAndCategoryCrossRefEntity::class)
    )
    val categories:List<MovieCategoryEntity>
)
