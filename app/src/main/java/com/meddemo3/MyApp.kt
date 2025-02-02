package com.meddemo3

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.meddemo3.db.AppDatabase
import com.meddemo3.utils.MyLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        myApp = this.applicationContext as MyApp
        MyLogger.d("MyApp - onCreate")
//        setUncaughtException()
    }

    companion object {
        lateinit var myApp: MyApp
        fun getInstance(): MyApp {
            return myApp
        }
    }

    fun createDatabase() {
        database = databaseBuilder(
            this, AppDatabase::
            class.java, "database"
        ).build()
        if (database == null){
            MyLogger.e("MyApp - error creating database")
        }
    }

    fun getDatabase(): AppDatabase? {
        if (database == null){
            createDatabase()
        }
        return database
    }




}
