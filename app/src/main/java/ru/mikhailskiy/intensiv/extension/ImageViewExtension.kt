package ru.mikhailskiy.intensiv.extension

import android.widget.ImageView
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun ImageView.setImageFromBackend(url:String?, transformation: Transformation? = null)
{
    url?.let {
        val picasso = Picasso.get()
            .load(ru.mikhailskiy.intensiv.BuildConfig.IMAGE_URL + it)

        if (transformation != null)
            picasso.transform(transformation)

        picasso.into(this)
    }
}