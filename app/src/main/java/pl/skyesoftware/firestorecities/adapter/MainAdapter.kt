package pl.skyesoftware.firestorecities.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.util.SortedListAdapterCallback
import android.view.ViewGroup
import pl.skyesoftware.firestorecities.adapter.delegate.CityDelegateAdapter
import pl.skyesoftware.firestorecities.adapter.delegate.ModelTypeDelegateAdapter
import pl.skyesoftware.firestorecities.data.model.City
import pl.skyesoftware.firestorecities.data.model.ModelType

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ModelTypeDelegateAdapter>()

    private var itemsSortedList: SortedList<ModelType> = SortedList(ModelType::class.java, object : SortedListAdapterCallback<ModelType>(this) {
        override fun areContentsTheSame(oldItem: ModelType?, newItem: ModelType?): Boolean {
            if (oldItem is City && newItem is City) {
                return oldItem.id == newItem.id
            }
            return false
        }

        override fun areItemsTheSame(oldItem: ModelType?, newItem: ModelType?): Boolean {
            if (oldItem is City && newItem is City) {
                return oldItem.name == newItem.name
            }
            return false
        }

        override fun compare(oldItem: ModelType?, newItem: ModelType?): Int {
            if (oldItem is City && newItem is City) {
                return newItem.population.compareTo(oldItem.population)
            }
            return 1
        }
    })

    init {
        delegateAdapters.append(ModelType.Types.CITY, CityDelegateAdapter())
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return itemsSortedList.size()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,
                this.itemsSortedList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.itemsSortedList[position].getModelType()
    }

    override fun getItemId(position: Int): Long {
        return itemsSortedList[position].hashCode().toLong()
    }

    fun clearListAndAddNewItems(mutableList: MutableList<ModelType>) {
        itemsSortedList.beginBatchedUpdates()
        itemsSortedList.clear()
        itemsSortedList.endBatchedUpdates()
        addItems(mutableList)
    }

    fun addItems(mutableList: MutableList<ModelType>) {
        itemsSortedList.beginBatchedUpdates()
        mutableList.forEach {
            itemsSortedList.add(it)
        }
        itemsSortedList.endBatchedUpdates()
    }

    fun getItemsList() : SortedList<ModelType> {
        return itemsSortedList
    }


}