package com.meddemo3.db

import androidx.room.Query
import com.meddemo3.dbdata.DoctorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DoctorsRepo(private val doctorDao: DoctorDao) {
    suspend fun insertDoctor(doctor: DoctorEntity) {
        doctorDao.insertDoctor(doctor)
    }

    suspend fun getDoctors(): List<DoctorEntity> {
        return doctorDao.getDoctors()
    }

    suspend fun deleteDoctorById(id: Int) {
        doctorDao.deleteDoctorById(id)
    }

}