package ru.mikhailskiy.intensiv.ui.movie_details

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.layout_actor.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.actor.Actor

class ActorItem(val content: Actor, val onItemClicked: (actor: Actor) -> Unit) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_full_name.text = content.fullName?.replace(" ", "\n")

        viewHolder.itemView.setOnClickListener {
            onItemClicked(content)
        }

        content.imageUrl?.let {
            Picasso.get().load(it)
                .transform(CropCircleTransformation())
                .into(viewHolder.image_actor)
        }
    }

    override fun getLayout() = R.layout.layout_actor
}