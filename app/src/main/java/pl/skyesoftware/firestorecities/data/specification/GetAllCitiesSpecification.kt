package pl.skyesoftware.firestorecities.data.specification

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import pl.skyesoftware.firestorecities.RxFirestore
import pl.skyesoftware.firestorecities.data.model.City

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class GetAllCitiesSpecification : Specification<City> {

    override fun query(firestore: FirebaseFirestore): Observable<MutableList<City>> {
        return RxFirestore.query(getQuery(firestore)).map {
            val citiesList = mutableListOf<City>()
            it.documents.mapTo(citiesList) { it.toObject(City::class.java) }
            return@map citiesList
        }
    }

    private fun getQuery(firestore: FirebaseFirestore) : Query {
        return firestore.collection("cities").orderBy("population", Query.Direction.DESCENDING)
    }
}