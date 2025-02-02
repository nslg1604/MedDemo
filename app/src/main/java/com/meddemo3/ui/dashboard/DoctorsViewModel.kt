package com.meddemo3.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.meddemo3.MyApp
import com.meddemo3.common.MyUrls
import com.meddemo3.utils.MyLogger
import com.meddemo3.common.MyInfo
import com.meddemo3.data.DoctorsResponse
import com.meddemo3.db.DoctorsRepo
import com.meddemo3.dbdata.DoctorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.squareup.moshi.Moshi
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.Android
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

//@HiltViewModel
class DoctorsViewModel : ViewModel() {
    private val client = HttpClient(Android)

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var text: LiveData<String> = _text

    val doctorsResponse: MutableLiveData<DoctorsResponse> by lazy {
        MutableLiveData<DoctorsResponse>()
    }

//    private var _progressStarted = MutableLiveData<Boolean>().apply {
//        value = true
//    }
//    var progressStarted: LiveData<Boolean> = _progressStarted

//    var progressStarted: MutableLiveData<Boolean>{
//
//    }

    val progressStarted: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val progressStr: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    fun getDoctorsAsync() {
        MyLogger.d("DoctorsViewModel - getDoctorsAsync url=" + MyUrls.GET_ACTUAL)
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.get(
                    MyUrls.GET_DOCTORS
                ) {
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    }
                }

                this@DoctorsViewModel.progressStr.value = response.status.value.toString()
                if (response.status.value != 200) {
                    withContext(Dispatchers.Main) {
                        MyLogger.e("Server error: ${response.status.value}")
                    }
                    this@DoctorsViewModel.progressStarted.value = false
                    return@launch
                }

                val responseBody: String = response.body()
                MyLogger.d("getDoctorsAsync response=" + responseBody)

                // Инициализируем Moshi и адаптер
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter = moshi.adapter(DoctorsResponse::class.java)

                val doctorsResponse = try {
                    jsonAdapter.fromJson(responseBody)
                } catch (e: JsonDataException) {
                    MyLogger.e("DoctorsViewModel - JSON parsing error=" + e)
                    this@DoctorsViewModel.progressStarted.value = false
                    null
                }

                withContext(Dispatchers.Main) {
                    if (doctorsResponse != null) {
                        MyLogger.d("Parsed Data: $doctorsResponse")
                        MyLogger.d("doctors.size: ${doctorsResponse.doctors.size}")
                        this@DoctorsViewModel.doctorsResponse.value = doctorsResponse
                        this@DoctorsViewModel.progressStarted.value = false
                    } else {
                        MyLogger.e("DoctorsViewModel - Failed to parse JSON")
                        this@DoctorsViewModel.progressStarted.value = false
                    }
                }
            } catch (e: ConnectTimeoutException) {
                MyLogger.e("DoctorsViewModel - no internet connection=" + e)
            } catch (e: Exception) {
                MyLogger.e("DoctorsViewModel - error fetching data=" + e)
            }
        }
    }

    fun addDoctorsToDatabaseAsync() {
        MyLogger.d("DoctorsViewModel - addDoctorsToDatabaseAsync url=" + MyUrls.GET_ACTUAL)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    addDoctorsToDatabase()
                } catch (e: Exception) {
                    MyLogger.e("DoctorsViewModel - addDoctorsToDatabaseAsync error=" + e)
                }
            }
        }
    }

    suspend fun addDoctorsToDatabase() {
        MyLogger.d("DoctorsViewModel - addDoctorsToDatabase")
        val db = MyApp.getInstance().getDatabase()
        if (db == null) {
            MyLogger.e("DoctorsViewModel - addDoctorsToDatabase db=null")
            return
        }
        MyLogger.d("DoctorsViewModel - addDoctorsToDatabase doctorsResponse.value!!.doctors=" + doctorsResponse.value!!.doctors)
        if (doctorsResponse.value?.doctors == null) {
            MyLogger.d("DoctorsViewModel - addDoctorsToDatabase null")
        }
        MyLogger.d("DoctorsViewModel - addDoctorsToDatabase doctorsResponse.value!!.doctors=" + doctorsResponse.value!!.doctors.size)
        val doctorDao = db.doctorDao()
        val doctorsRepo = DoctorsRepo(doctorDao = doctorDao)
        for (doctor in doctorsResponse.value!!.doctors) {
            MyLogger.d("DoctorsViewModel - addDoctorsToDatabase doctor.id=" + doctor.id + " name=" + doctor.fullName)
            if (doctor.id != null) {
                val doctorEntity = DoctorEntity(
                    doctor.id,
                    doctor.fullName ?: "",
                    doctor.img ?: ""
                )
                doctorsRepo.insertDoctor(doctorEntity)
            }
            val doctorEntities: List<DoctorEntity> = doctorDao.getDoctors()
            MyLogger.d("DoctorsViewModel - addDoctorsToDatabase doctorEntities.size=" + doctorEntities?.size)

        }
    }

    fun showDoctors() {
        if (MyInfo.doctorsResponse == null) {
            getDoctorsAsync()
        } else {
            doctorsResponse.value = MyInfo.doctorsResponse
        }
    }


}