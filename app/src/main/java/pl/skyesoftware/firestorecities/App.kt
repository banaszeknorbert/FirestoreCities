package pl.skyesoftware.firestorecities

import android.app.Application
import android.support.multidex.MultiDex
import com.google.firebase.FirebaseApp

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        FirebaseApp.initializeApp(this@App)
    }
}