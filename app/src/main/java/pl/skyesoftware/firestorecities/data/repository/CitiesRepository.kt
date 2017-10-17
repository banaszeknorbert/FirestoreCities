package pl.skyesoftware.firestorecities.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.firestorecities.addValue
import pl.skyesoftware.firestorecities.data.model.City
import pl.skyesoftware.firestorecities.data.specification.Specification
import pl.skyesoftware.firestorecities.removeValue
import pl.skyesoftware.firestorecities.updateValue

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class CitiesRepository : Repository<City> {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun add(item: City): Completable {
        return firestore.collection("cities").document(item.id).addValue(item)
    }

    override fun remove(item: City): Completable {
        return firestore.collection("cities").document(item.id).removeValue()
    }

    override fun query(specification: Specification<City>): Observable<MutableList<City>> {
        return specification.query(firestore)
    }

    override fun first(specification: Specification<City>): Observable<City> {
        return specification.query(firestore).map { return@map it[0] }
    }
}