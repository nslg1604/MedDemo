package com.meddemo3.db

import androidx.room.Query
import com.meddemo3.dbdata.DoctorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DoctorsRepo(private val doctorDao: DoctorDao) {
    suspend fun insertDoctor(doctor: DoctorEntity) {
        withContext(Dispatchers.IO) {
            doctorDao.insertDoctor(doctor)
        }
    }

    suspend fun getDoctors(): List<DoctorEntity>{
        return withContext(Dispatchers.IO) {
            return@withContext doctorDao.getDoctors()
        }
    }

    suspend fun deleteDoctorById(id: Int){
        withContext(Dispatchers.IO) {
            doctorDao.deleteDoctorById(id)
        }
    }

}