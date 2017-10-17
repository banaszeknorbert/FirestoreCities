package pl.skyesoftware.firestorecities.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pl.skyesoftware.firestorecities.data.model.ModelType

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

interface ModelTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ModelType)

    fun onViewRecycled(holder: RecyclerView.ViewHolder?)

}