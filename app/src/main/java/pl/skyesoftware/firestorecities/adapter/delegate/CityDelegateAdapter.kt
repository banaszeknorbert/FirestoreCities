package pl.skyesoftware.firestorecities.adapter.delegate

import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import pl.skyesoftware.firestorecities.R
import pl.skyesoftware.firestorecities.data.model.City
import pl.skyesoftware.firestorecities.data.model.ModelType
import pl.skyesoftware.firestorecities.extensions.inflate
import pl.skyesoftware.firestorecities.extensions.loadImg
import pl.skyesoftware.firestorecities.viper.detail.CityDetailsActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class CityDelegateAdapter : ModelTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = CityViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ModelType) {
        holder as CityViewHolder
        holder.fillView(item as City)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CityViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.viewholder_city)) {

        private val cityImageView = itemView.findViewById<ImageView>(R.id.cityImageView)
        private val cityName = itemView.findViewById<TextView>(R.id.cityName)
        private val countryName = itemView.findViewById<TextView>(R.id.countryName)
        private val stateName = itemView.findViewById<TextView>(R.id.stateName)
        private val cityPopulation = itemView.findViewById<TextView>(R.id.cityPopulation)

        fun fillView(city: City) {
            setTransitionNames()
            cityImageView.loadImg(city.imageUrl)
            cityName.text = city.name
            cityPopulation.text = getFormattedString(R.string.city_population_placeholder, NumberFormat.getNumberInstance(Locale.US).format(city.population))
            countryName.text = getFormattedString(R.string.city_country_placeholder, city.country)
            stateName.text = getFormattedString(R.string.city_state_placeholder, city.state)
            itemView.setOnClickListener {
                val cityDetailsObject = City.createCityDetailsObjectFromCityForPosition(city, adapterPosition)
                CityDetailsActivity.start(itemView.context, cityDetailsObject, cityImageView, cityName)
            }
        }

        private fun setTransitionNames() {
            cityName.transitionName = "nameTransition_" + adapterPosition
            stateName.transitionName = "stateTransition_" + adapterPosition
            countryName.transitionName = "countryTransition_" + adapterPosition
            cityPopulation.transitionName = "populationTransition_" + adapterPosition
            cityImageView.transitionName = "imageTransition_" + adapterPosition
        }

        private fun getFormattedString(@StringRes resId: Int, value: Any) : String {
            return String.format(Locale.getDefault(),
                    itemView.context.getString(resId), value)
        }

    }
}