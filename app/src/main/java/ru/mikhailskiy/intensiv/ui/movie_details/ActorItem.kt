package ru.mikhailskiy.intensiv.ui.movie_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.layout_actor.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.credit.Credit
import ru.mikhailskiy.intensiv.extension.setImageFromBackend

class ActorItem(private val content: Credit, val onItemClicked: (credit: Credit) -> Unit) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.full_name_text.text = content.name?.replace(" ", "\n")

        viewHolder.itemView.setOnClickListener {
            onItemClicked(content)
        }

        viewHolder.actor_image.setImageFromBackend(content.profilePath, transformation = CropCircleTransformation())
    }

    override fun getLayout() = R.layout.layout_actor
}