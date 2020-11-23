package ru.mikhailskiy.intensiv.common

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils.dpToPx

class VerticalSpaceDecoration (private val verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    @SuppressLint("RestrictedApi")
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = dpToPx(view.context,verticalSpaceHeight).toInt()
    }
}