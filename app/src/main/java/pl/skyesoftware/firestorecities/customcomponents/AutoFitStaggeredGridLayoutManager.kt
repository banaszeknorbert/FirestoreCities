package pl.skyesoftware.firestorecities.customcomponents

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class AutoFitStaggeredGridLayoutManager(private val context: Context, columnWidth: Int, orientation: Int) : StaggeredGridLayoutManager(1, orientation) {

    private var columnWidth: Int = 0
    private var columnWidthChanged = true

    init {

        setColumnWidth(columnWidth)
    }

    fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth
            columnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        if (columnWidthChanged && columnWidth > 0) {
            val totalSpace: Int
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                totalSpace = width - paddingRight - paddingLeft
            } else {
                totalSpace = height - paddingTop - paddingBottom
            }
            val spanCount = Math.max(1, totalSpace / columnWidth)
            Handler(context.mainLooper).post { setSpanCount(spanCount) }
            columnWidthChanged = false
        }
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: Exception) {
            Log.e("probe", "meet a IOOBE in RecyclerView")
        }

    }
}
