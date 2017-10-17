package pl.skyesoftware.firestorecities.data.model

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

interface ModelType {

    fun getModelType(): Int

    object Types {
        val CITY = 1
    }

}