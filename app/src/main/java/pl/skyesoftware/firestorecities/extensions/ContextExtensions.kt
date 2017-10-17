package pl.skyesoftware.firestorecities.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

fun Context.checkIsTablet() : Boolean {
    var isTablet: Boolean = false
    val display: Display = (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val metrics: DisplayMetrics = DisplayMetrics()
    display.getMetrics(metrics)
    val widthInches = metrics.widthPixels / metrics.xdpi
    val heightInches = metrics.heightPixels / metrics.ydpi
    val diagonalInches = Math.sqrt(Math.pow(widthInches.toDouble(), 2.0) + Math.pow(heightInches.toDouble(), 2.0))
    if (diagonalInches >= 7.0) {
        isTablet = true
    }
    return isTablet
}