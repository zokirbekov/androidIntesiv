package ru.mikhailskiy.intensiv.presentation.watchlist

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDto
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class MoviePreviewItem(
    private val content: MovieVo,
    private val onClick: (movie: MovieVo) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_preview.setImageFromBackend(content.posterPath)
    }
}