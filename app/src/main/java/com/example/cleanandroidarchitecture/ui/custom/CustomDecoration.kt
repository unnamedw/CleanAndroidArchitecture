package com.example.cleanandroidarchitecture.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanandroidarchitecture.ui.adapter.PostAdapter

class CustomDecoration(context: Context) : RecyclerView.ItemDecoration() {

    companion object {
        private const val VIEW_TYPE_FIRST_DIVIDER_COLOR = Color.BLUE
        private const val VIEW_TYPE_SECOND_DIVIDER_COLOR = Color.RED
    }

    private val viewTypeFirstPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = VIEW_TYPE_FIRST_DIVIDER_COLOR
    }
    private val viewTypeSecondPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = VIEW_TYPE_SECOND_DIVIDER_COLOR
    }

    private val viewTypeFirstHeightDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        1f, context.resources.displayMetrics).toInt()
    private val viewTypeFirstLeftMarginDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        20f, context.resources.displayMetrics).toInt()
    private val viewTypeFirstRightMarginDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        20f, context.resources.displayMetrics).toInt()

    private val viewTypeSecondHeightDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        2f, context.resources.displayMetrics).toInt()
    private val viewTypeSecondLeftMarginDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        40f, context.resources.displayMetrics).toInt()
    private val viewTypeSecondRightMarginDp: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        40f, context.resources.displayMetrics).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val viewType = parent.adapter?.getItemViewType(position)
        if (viewType == PostAdapter.VIEW_TYPE_FIRST) {
            outRect.set(0, 0, 0, viewTypeFirstHeightDp)
        } else if (viewType == PostAdapter.VIEW_TYPE_SECOND) {
            outRect.set(0, 0, 0, viewTypeSecondHeightDp)
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEach { childView ->
            val position = parent.getChildAdapterPosition(childView)
            val viewType = parent.adapter?.getItemViewType(position)
            if (viewType == PostAdapter.VIEW_TYPE_FIRST) {
                c.drawRect(
                    childView.left.toFloat() + viewTypeFirstLeftMarginDp,
                    childView.bottom.toFloat(),
                    childView.right.toFloat() - viewTypeFirstRightMarginDp,
                    (childView.bottom + viewTypeFirstHeightDp).toFloat(),
                    viewTypeFirstPaint
                )
            } else if (viewType == PostAdapter.VIEW_TYPE_SECOND) {
                c.drawRect(
                    childView.left.toFloat() + viewTypeSecondLeftMarginDp,
                    childView.bottom.toFloat(),
                    childView.right.toFloat() - viewTypeSecondRightMarginDp,
                    (childView.bottom + viewTypeSecondHeightDp).toFloat(),
                    viewTypeSecondPaint
                )
            }

        }
    }

}