package ru.mikhailskiy.intensiv.data.entity.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.mikhailskiy.intensiv.data.entity.MovieCategoryEntity

data class CategoryWithMovie(
    @Embedded val category:MovieCategoryEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "movieId",
        associateBy = Junction(MovieAndCategoryCrossRefEntity::class)
    )
    val movies:List<MovieEntity>
)