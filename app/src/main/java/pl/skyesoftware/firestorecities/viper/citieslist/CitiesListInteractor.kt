package pl.skyesoftware.firestorecities.viper.citieslist

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor
import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter
import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.firestorecities.data.model.City
import pl.skyesoftware.firestorecities.data.repository.CitiesRepository
import pl.skyesoftware.firestorecities.data.specification.GetAllCitiesSpecification

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class CitiesListInteractor : BaseRxInteractor(), CitiesListContract.Interactor {

    private val citiesRepository = CitiesRepository()

    override fun addMockedCities(citiesList: MutableList<City>): Completable {
        val completablesList = mutableListOf<Completable>()
        citiesList.forEach {
            completablesList.add(citiesRepository.add(it))
        }
        return Completable.merge(completablesList)
    }

    override fun getCitiesList(): Observable<MutableList<City>> {
        return citiesRepository.query(GetAllCitiesSpecification())
    }

}