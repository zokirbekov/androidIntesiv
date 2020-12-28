package ru.mikhailskiy.intensiv.presentation.search

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie_for_vertical.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.dto.movie.MovieDto
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class SearchResultItem(
    private val content: MovieVo,
    private val onClick: (movie: MovieVo) -> Unit
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
//        viewHolder.show_rating.rating = content.rating
        viewHolder.text_show_name.text = content.title
        viewHolder.itemView.setOnClickListener {
            onClick(content)
        }

        viewHolder.image_preview.setImageFromBackend(content.backdropPath)
    }

    override fun getLayout() = R.layout.item_movie_for_vertical
}