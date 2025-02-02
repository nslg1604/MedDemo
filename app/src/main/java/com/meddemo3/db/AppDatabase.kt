package com.meddemo3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meddemo3.dbdata.DoctorEntity

@Database(
    version = 1,
    entities = [
        DoctorEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
}