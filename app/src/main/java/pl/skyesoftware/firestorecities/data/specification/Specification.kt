package pl.skyesoftware.firestorecities.data.specification

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

interface Specification<T> {

    fun query(firestore: FirebaseFirestore) : Observable<MutableList<T>>

}