package ru.mikhailskiy.intensiv.ui.watchlist

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class MoviePreviewItem(
    private val content: MovieDto,
    private val onClick: (movie: MovieDto) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }

        viewHolder.image_preview.setImageFromBackend(content.posterPath)

    }
}