package ru.mikhailskiy.intensiv.ui.search

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie_for_vertical.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class SearchResultItem(
    private val content: MovieDto,
    private val onClick: (movie: MovieDto) -> Unit
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.show_rating.rating = content.rating
        viewHolder.text_show_name.text = content.title
        viewHolder.itemView.setOnClickListener {
            onClick(content)
        }

        viewHolder.image_preview.setImageFromBackend(content.backdropPath)
    }

    override fun getLayout() = R.layout.item_movie_for_vertical
}