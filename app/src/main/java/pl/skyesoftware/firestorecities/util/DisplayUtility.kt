package pl.skyesoftware.firestorecities.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

object DisplayUtility {

    // region Utility Methods
    fun dp2px(context: Context, dp: Int): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        val displaymetrics = DisplayMetrics()
        display.getMetrics(displaymetrics)

        return (dp * displaymetrics.density + 0.5f).toInt()
    }

    fun px2dp(context: Context, px: Int): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        val displaymetrics = DisplayMetrics()
        display.getMetrics(displaymetrics)

        return (px / displaymetrics.density + 0.5f).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        val size = Point()
        (context as Activity).windowManager.defaultDisplay.getSize(size)
        return size.x
    }

    fun getScreenHeight(context: Context): Int {
        val size = Point()
        (context as Activity).windowManager.defaultDisplay.getSize(size)
        return size.y
    }

    fun isInLandscapeMode(context: Context): Boolean {
        var isLandscape = false
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true
        }
        return isLandscape
    }

    fun hideKeyboard(context: Context?, view: View?) {
        if (context != null) {
            val inputMethodManager = context.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            if (inputMethodManager != null) {
                if (view != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
    }

    fun showKeyboard(context: Context, view: View) {
        view.requestFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
    // endregion
}
