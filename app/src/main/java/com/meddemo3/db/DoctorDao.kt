package com.meddemo3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meddemo3.dbdata.DoctorEntity

@Dao
interface DoctorDao {
    @Insert(entity = DoctorEntity::class)
    fun insertDoctor(doctor: DoctorEntity)

    @Query("SELECT id, name, image FROM doctors\n")
    fun getDoctors(): List<DoctorEntity>

    @Query("DELETE FROM doctors WHERE id = :id")
    fun deleteDoctorById(id: Int)
}