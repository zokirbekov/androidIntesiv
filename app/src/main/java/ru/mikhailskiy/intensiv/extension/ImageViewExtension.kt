package ru.mikhailskiy.intensiv.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import ru.mikhailskiy.intensiv.BuildConfig

fun ImageView.setImageFromBackend(url:String?, baseUrl:String = BuildConfig.IMAGE_URL, transformation: Transformation? = null)
{
    url?.let {
        val picasso = Picasso.get()
            .load(baseUrl + it)

        if (transformation != null)
            picasso.transform(transformation)

        picasso.into(this)
    }
}