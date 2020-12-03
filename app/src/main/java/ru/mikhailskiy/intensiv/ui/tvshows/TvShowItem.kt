package ru.mikhailskiy.intensiv.ui.tvshows

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_movie_for_vertical.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.tvShow.TvShow
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class TvShowItem(
    private val content:TvShow,
    private val onClick : (tvShow: TvShow) -> Unit
) : com.xwray.groupie.kotlinandroidextensions.Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.show_rating.rating = content.rating
        viewHolder.text_show_name.text = content.name
        viewHolder.itemView.setOnClickListener {
            onClick(content)
        }

        viewHolder.image_preview.setImageFromBackend(content.backdropPath)
    }

    override fun getLayout() = R.layout.item_movie_for_vertical

}