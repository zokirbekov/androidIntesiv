package ru.mikhailskiy.intensiv.presentation.common

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils

class HorizontalSpaceDecoration (private val horizontalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    @SuppressLint("RestrictedApi")
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = ViewUtils.dpToPx(view.context, horizontalSpaceHeight).toInt()
    }
}