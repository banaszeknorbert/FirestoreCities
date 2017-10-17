package pl.skyesoftware.firestorecities

import io.reactivex.functions.Cancellable
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.internal.disposables.DisposableHelper.isDisposed
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe



/**
 * Created by norbertbanaszek on 17.10.2017.
 */

object RxFirestore {

    fun query(query: Query): Observable<QuerySnapshot> {
        return Observable.create({ e ->
            query.get().addOnSuccessListener({ documentSnapshots ->
                if (!e.isDisposed) {
                    e.onNext(documentSnapshots)
                }
            }).addOnFailureListener(OnFailureListener { exception -> e.onError(exception) })
            e.setCancellable { }
        })
    }
}

//extensions
fun DocumentReference.updateValue(name: String, value: Any): Completable {
    return Completable.create { source ->
        this.update(name, value)
                .addOnCompleteListener {
                    source.onComplete()
                }
                .addOnFailureListener {
                    source.onError(it)
                }
    }
}

fun DocumentReference.updateValues(updatesMap: Map<String, Any>): Completable {
    return Completable.create { source ->
        this.update(updatesMap)
                .addOnCompleteListener {
                    source.onComplete()
                }
                .addOnFailureListener {
                    source.onError(it)
                }
    }
}

fun DocumentReference.addValue(value: Any): Completable {
    return Completable.create { source ->
        this.set(value)
                .addOnCompleteListener {
                    source.onComplete()
                }
                .addOnFailureListener {
                    source.onError(it)
                }
    }
}

fun DocumentReference.removeValue(): Completable {
    return Completable.create { source ->
        this.delete()
                .addOnCompleteListener {
                    source.onComplete()
                }
                .addOnFailureListener {
                    source.onError(it)
                }
    }
}

fun DocumentReference.setValue(value: Any): Completable {
    return Completable.create { source ->
        this.set(value)
                .addOnCompleteListener {
                    source.onComplete()
                }
                .addOnFailureListener {
                    source.onError(it)
                }
    }
}