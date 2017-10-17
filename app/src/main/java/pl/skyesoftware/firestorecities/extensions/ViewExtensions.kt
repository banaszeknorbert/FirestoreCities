package pl.skyesoftware.firestorecities.extensions

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

import android.content.res.Configuration
import android.support.annotation.LayoutRes
import android.support.v4.view.AsyncLayoutInflater
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pl.skyesoftware.firestorecities.customcomponents.AutoFitStaggeredGridLayoutManager
import pl.skyesoftware.firestorecities.customcomponents.SpacingItemDecorator
import pl.skyesoftware.firestorecities.util.DisplayUtility
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions




fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.loadAsync(@LayoutRes layoutId: Int, action: View.() -> Unit) {
    AsyncLayoutInflater(context).inflate(layoutId, null) {
        view, resId, parent ->
        with(parent) {
            addView(view)
            action(view)
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImg(imageUrl: String) {
    if (imageUrl.isEmpty()) {
        Log.e("LoadImg", "ImageUrl is empty")
    } else {
        val options = RequestOptions()
        options.centerCrop()
        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        Glide.with(context).load(imageUrl).apply(options).into(this)
    }
}

fun RecyclerView.provideProperLayoutManager(spacing: Int = 3) {
    val itemDecorator: RecyclerView.ItemDecoration
    val recyclerView = this
    if (context.checkIsTablet()) {
        val gridLayoutManager: AutoFitStaggeredGridLayoutManager
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = AutoFitStaggeredGridLayoutManager(context, (resources?.displayMetrics?.widthPixels!! / 2).toInt(), StaggeredGridLayoutManager.VERTICAL)
            itemDecorator = SpacingItemDecorator(2, DisplayUtility.dp2px(this.context, spacing), true)
        } else {
            gridLayoutManager = AutoFitStaggeredGridLayoutManager(context, (resources?.displayMetrics?.widthPixels!! / 3).toInt(), StaggeredGridLayoutManager.VERTICAL)
            itemDecorator = SpacingItemDecorator(3, DisplayUtility.dp2px(this.context, spacing), true)
        }
        this.addItemDecoration(itemDecorator)
        gridLayoutManager.isItemPrefetchEnabled = true
        this.layoutManager = gridLayoutManager
    } else {
        val linearLayoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun getExtraLayoutSpace(state: RecyclerView.State?): Int {
                return DisplayUtility.getScreenHeight(recyclerView.context)
            }
        }
        itemDecorator = SpacingItemDecorator(1, DisplayUtility.dp2px(this.context, spacing), true)
        this.addItemDecoration(itemDecorator)
        linearLayoutManager.isItemPrefetchEnabled = true
        this.layoutManager = linearLayoutManager
        linearLayoutManager.setInitialPrefetchItemCount(5)
    }
//    this.setItemViewCacheSize(5)
}