package ru.mikhailskiy.intensiv.ui.tvshows

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.layout_tv_show.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.tvShow.TvShow

class TvShowItem(
    private val content:TvShow,
    private val onClick : (tvShow: TvShow) -> Unit
) : com.xwray.groupie.kotlinandroidextensions.Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.show_rating.rating = content.rating
        viewHolder.text_show_name.text = content.title
        viewHolder.itemView.setOnClickListener {
            onClick(content)
        }

        Picasso.get()
            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
            .into(viewHolder.image_preview)
    }

    override fun getLayout() = R.layout.layout_tv_show

}