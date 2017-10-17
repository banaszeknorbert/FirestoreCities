package pl.skyesoftware.firestorecities.viper.citieslist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.mateuszkoslacz.moviper.base.view.activity.ViperActivity
import kotlinx.android.synthetic.main.activity_cities_list.*
import pl.skyesoftware.firestorecities.R
import pl.skyesoftware.firestorecities.adapter.MainAdapter
import pl.skyesoftware.firestorecities.data.model.City
import pl.skyesoftware.firestorecities.data.model.ModelType
import pl.skyesoftware.firestorecities.extensions.hide
import pl.skyesoftware.firestorecities.extensions.provideProperLayoutManager
import pl.skyesoftware.firestorecities.extensions.show

class CitiesListActivity : ViperActivity<CitiesListContract.View, CitiesListContract.Presenter>(), CitiesListContract.View {

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)
        setupToolbar()
        setupRecyclerView()
        emptyListMockDataLabel.setOnClickListener {
            presenter.mockCitiesData()
        }
        addNewCityButton.setOnClickListener {
            Toast.makeText(it.context, "Not implemented yet.", Toast.LENGTH_SHORT).show()
        }
        presenter.onInit()
    }

    private fun setupToolbar() {
        setSupportActionBar(citiesListToolbar)
        citiesListToolbar.setBackgroundColor(ContextCompat.getColor(this@CitiesListActivity, R.color.black))
        citiesListToolbar.setTitleTextColor(ContextCompat.getColor(this@CitiesListActivity, R.color.white))
    }

    private fun setupRecyclerView() {
        citiesRecyclerView.provideProperLayoutManager(4)
        citiesRecyclerView.adapter = mainAdapter
    }

    override fun createPresenter(): CitiesListContract.Presenter {
        return CitiesListPresenter()
    }

    override fun showCitiesList(citiesList: MutableList<City>) {
        val modelTypeList = mutableListOf<ModelType>()
        citiesList.mapTo(modelTypeList) { it }
        mainAdapter.addItems(modelTypeList)
    }

    override fun hideLoading() {
        citiesListProgressView.hide()
        citiesRecyclerView.show()
    }

    override fun showEmptyListInfo() {
        emptyListMockDataLabel.show()
        citiesRecyclerView.hide()
    }

    override fun showLoading() {
        citiesListProgressView.show()
        citiesRecyclerView.hide()
    }

    override fun hideEmptyListInfo() {
        emptyListMockDataLabel.hide()
        citiesRecyclerView.show()
    }
}
