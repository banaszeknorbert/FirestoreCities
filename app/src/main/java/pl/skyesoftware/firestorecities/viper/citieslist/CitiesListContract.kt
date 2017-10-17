package pl.skyesoftware.firestorecities.viper.citieslist

import android.app.Activity
import android.widget.FrameLayout
import com.hannesdorfmann.mosby.mvp.MvpView
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting
import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.firestorecities.data.model.City

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

interface CitiesListContract {

    interface Presenter : ViperPresenter<View> {
        fun onInit()
        fun mockCitiesData()
    }

    interface View : MvpView {
        fun showCitiesList(citiesList: MutableList<City>)
        fun hideLoading()
        fun showLoading()
        fun showEmptyListInfo()
        fun hideEmptyListInfo()
    }

    interface Interactor : ViperRxInteractor {
        fun getCitiesList() : Observable<MutableList<City>>
        fun addMockedCities(citiesList: MutableList<City>) : Completable
    }

    interface Routing : ViperRxRouting<Activity>


}