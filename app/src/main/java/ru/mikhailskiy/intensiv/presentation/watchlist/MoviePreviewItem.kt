package ru.mikhailskiy.intensiv.presentation.watchlist

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDto

class MoviePreviewItem(
    private val content: MovieDto,
    private val onClick: (movie: MovieDto) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }
        // TODO Получать из модели
        Picasso.get()
            .load("https://www.kinopoisk.ru/images/film_big/1143242.jpg")
            .into(viewHolder.image_preview)
    }
}