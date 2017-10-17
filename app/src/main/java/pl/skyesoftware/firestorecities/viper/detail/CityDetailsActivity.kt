package pl.skyesoftware.firestorecities.viper.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_city_details.*
import pl.skyesoftware.firestorecities.R
import pl.skyesoftware.firestorecities.extensions.loadImg
import pl.skyesoftware.firestorecities.viper.citieslist.CitiesListActivity


/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class CityDetailsActivity : AppCompatActivity() {

    companion object {

        val CITY_DETAILS_OBJECT_KEY = "cityDetailsObjectKey"

        fun start(source: Context, cityDetailsObject: CityDetailsObject, imageView: View, nameTextView: View) {
            val intent = Intent(source, CityDetailsActivity::class.java)
            intent.putExtra(CITY_DETAILS_OBJECT_KEY, Gson().toJson(cityDetailsObject))
            val activity = source as CitiesListActivity
            val toolbar = (activity).findViewById<View>(R.id.citiesListToolbar)
            val navigationBar = activity.findViewById<View>(android.R.id.navigationBarBackground)
            val p1 = android.support.v4.util.Pair(imageView, cityDetailsObject.imageTransition)
            val p2 = android.support.v4.util.Pair(nameTextView, cityDetailsObject.nameTransition)
            val p3 = android.support.v4.util.Pair(toolbar, "toolbar")
            val p4 = android.support.v4.util.Pair(navigationBar, "navigationBar")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(source as Activity, p1, p2, p3)
            source.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)
        val cityDetailsObject = getCityDetailsObjectFromIntent()
        if (cityDetailsObject != null) {
            setupToolbar(cityDetailsObject)
            fillCityDetailsView(cityDetailsObject)
        } else {
            finish()
        }
    }

    private fun setupToolbar(cityDetailsObject: CityDetailsObject) {
        setSupportActionBar(cityDetailsToolbar)
        toolbarTitle.text = cityDetailsObject.name
        cityDetailsToolbar.setNavigationIcon(R.drawable.ic_arrow_white)
        cityDetailsToolbar.setBackgroundColor(ContextCompat.getColor(this@CityDetailsActivity, R.color.black))
        cityDetailsToolbar.setNavigationOnClickListener {
            supportFinishAfterTransition()
        }
        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_white)
    }

    private fun fillCityDetailsView(cityDetailsObject: CityDetailsObject) {
        setTransitionNames(cityDetailsObject)
        cityImageView.loadImg(cityDetailsObject.imageUrl)
        cityName.text = cityDetailsObject.name
    }

    private fun setTransitionNames(cityDetailsObject: CityDetailsObject) {
        cityImageView.transitionName = cityDetailsObject.imageTransition
        cityName.transitionName = cityDetailsObject.nameTransition
    }

    private fun getCityDetailsObjectFromIntent() : CityDetailsObject? {
        return if (intent != null) {
            Gson().fromJson(intent.getStringExtra(CITY_DETAILS_OBJECT_KEY), CityDetailsObject::class.java)
        } else {
            null
        }
    }
}