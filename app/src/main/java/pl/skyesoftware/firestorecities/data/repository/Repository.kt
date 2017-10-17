package pl.skyesoftware.firestorecities.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.firestorecities.data.specification.Specification

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

interface Repository<T> {

    fun add(item: T) : Completable

    fun remove(item: T) : Completable

    fun query(specification: Specification<T>) : Observable<MutableList<T>>

    fun first(specification: Specification<T>) : Observable<T>

}