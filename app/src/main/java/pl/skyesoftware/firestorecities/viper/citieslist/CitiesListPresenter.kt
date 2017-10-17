package pl.skyesoftware.firestorecities.viper.citieslist

import android.util.Log
import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.skyesoftware.firestorecities.data.model.City

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class CitiesListPresenter: BaseRxPresenter<CitiesListContract.View, CitiesListContract.Interactor, CitiesListContract.Routing>(), CitiesListContract.Presenter {

    private val TAG = CitiesListPresenter::class.java.simpleName

    override fun onInit() {
        addSubscription(getCitiesList())
    }

    override fun mockCitiesData() {
        view?.showLoading()
        view?.hideEmptyListInfo()
        addSubscription(addMockedCitiesToFirestore(City.getMockedCitiesList()))
    }

    private fun getCitiesList() = interactor.getCitiesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ citiesList ->
                if (citiesList.isNotEmpty()) {
                    view?.hideLoading()
                    view?.showCitiesList(citiesList)
                } else {
                    view?.hideLoading()
                    view?.showEmptyListInfo()
                }
            }, { error ->
                Log.e(TAG, "An error occured while loading cities list - ${error.message}")
            })

    private fun addMockedCitiesToFirestore(citiesList: MutableList<City>) = interactor
            .addMockedCities(citiesList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                addSubscription(getCitiesList())
            }, { error ->
                Log.e(TAG, "An error occured while mocking cities list - ${error.message}")
            })


    override fun createRouting(): CitiesListContract.Routing {
        return CitiesListRouting()
    }

    override fun createInteractor(): CitiesListContract.Interactor {
        return CitiesListInteractor()
    }

}