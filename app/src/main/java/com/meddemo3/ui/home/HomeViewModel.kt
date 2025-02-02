package com.meddemo3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meddemo3.common.MyUrls
import com.meddemo3.utils.MyLogger
import com.meddemo3.common.MyInfo
import com.meddemo3.data.ActualsResponse
import com.meddemo3.data.Story
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
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

/**
 * App home page
 */
class HomeViewModel : ViewModel(), CoroutineScope {
//    private val client = HttpClient(CIO)
    private val client = HttpClient(Android)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val actualsResponse: MutableLiveData<ActualsResponse> by lazy {
        MutableLiveData<ActualsResponse>()
    }

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


//    fun showBanners() {
//        if (MyInfo.banners.isNullOrEmpty()) {
//            getBanners.getBannersAsync()
//        } else {
//            callback?.showBanners(MyInfo.banners!!)
//        }
//    }
//
//    fun showPopulars() {
//        if (MyInfo.popularsEnabled.isEmpty()) {
//            getPopulars.getPopularsAsync()
//        } else {
//            callback?.showPopulars(MyInfo.popularsEnabled)
//        }
//    }
//
//    fun showErrorUi(message: String) {
//        MyInfo.activity?.runOnUiThread {
//            callback?.showError(message)
//        }
//    }
//


    /**
     * {"status":"ok","data":[
     * {"type":"story","linkType":2,"name":"- 50% на прием врача-ревматолога",
     * "link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-01502de9ef1642490a209a31c55c72d9f9341f80-new-93.jpg",
     * "img_xs":null,
     * "item":{"images":[
     * {"url":"https://lk.k31.ru/uploads/actual/cache/o-e8c5dd7a14741a4f4df95487529f58aebaffa6e7-new-93.jpg",
     * "viewed":false,"type":"image"}],
     * "storiesItems":{"type":"Акция","link":"https://www.k31.ru/info/promo/50-discount-on-appointment-with-a-rheumatologist/","name":"Скидка 50% на прием врача-ревматолога","text":"В клинике К+31 Запад вы можете получить консультацию ревматолога Бирюк Екатерины Сергеевны со скидкой 50%","bgColor":"#ebeff2","class":""}}},{"type":"story","linkType":2,"name":"Курс процедур со скидкой","link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-513ca778f453e69430323671d30d5a2094125d86-new-97.jpg","img_xs":null,"item":{"images":[{"url":"https://lk.k31.ru/uploads/actual/cache/o-eb661cc10f25fbf5e70f48edf9d59ecb85e32fd5-new-97.jpg","viewed":false,"type":"image"}],"storiesItems":{"type":"Акция","link":"https://www.k31.ru/info/promo/radiance-effect-course-of-procedures-on-the-heleo-4-device-at-a-discount/","name":"Эффект Сияния: курс процедур на аппарате Heleo 4 со скидкой","text":"Хотите сияющую и упругую кожу? Это проще, чем кажется!\r\n","bgColor":"#ebeff2","class":""}}},{"type":"story","linkType":2,"name":"Сохрани женское здоровье","link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-650910fe60a90bb8bf412d1a89f6bca3db879e1f-new-94.jpg","img_xs":null,"item":{"images":[{"url":"https://lk.k31.ru/uploads/actual/cache/o-97a8161778fe35249ab2920aff3e1448b3cb607b-new-94.jpg","viewed":false,"type":"image"}],"storiesItems":{"type":"Акция","link":"https://www.k31.ru/info/promo/blooming-july-preserve-womens-health/","name":"Сохрани женское здоровье","text":"Специальное предложение на гинекологический чекап\r\n","bgColor":"#ebeff2","class":""}}},{"type":"story","linkType":2,"name":"- 20% на лазерное интимное лечение","link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-93eeae4db4e848896b7acd95c4c91ceede6881e8-new-55.jpg","img_xs":null,"item":{"images":[{"url":"https://lk.k31.ru/uploads/actual/cache/o-9d9ac75795fdeb99dfef1c6e6f5883fa29d37a4d-new-55.jpg","viewed":false,"type":"image"}],"storiesItems":{"type":"Акция","link":"https://www.k31.ru/info/promo/20-discount-for-laser-intimate-treatment/","name":"Скидка 20% на лазерное интимное лечение","text":"Предложение действует для всех женщин, независимо обращались ли вы ранее в нашу клинику или нет.","bgColor":"#ebeff2","class":""}}},{"type":"story","linkType":2,"name":"Скидка 30% на маникюр и педикюр","link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-f6c2055a9aa0b746b5e2d74f8b152ae6591ec8e1-new-66.jpg","img_xs":null,"item":{"images":[{"url":"https://lk.k31.ru/uploads/actual/cache/o-118b48819f756a2cc4da141ddafa83daf17f2d42-new-66.jpg","viewed":false,"type":"image"}],"storiesItems":{"type":"Акция","link":"https://www.k31.ru/info/promo/30-discount-on-medical-manicure-and-pedicure-services/","name":"Скидка 30% на услуги медицинского маникюра и педикюра","text":"Идеальный профессиональный уход за Вашими ногтями и стопами!\r\n","bgColor":"#ebeff2","class":""}}},{"type":"story","linkType":2,"name":"-20% на экспресс обследование","link":null,"img":"https://lk.k31.ru/uploads/actual/cache/o-3d8b107034bc4baeeb653674e7d9a4ac0bed4166-
     */
    fun getActualsAsync() {
        MyLogger.d("HomeViewModel - getActualsAsync url=" + MyUrls.GET_ACTUAL)
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.get(
                    MyUrls.GET_ACTUAL){
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    }
                }

                if (response.status.value != 200) {
                    withContext(Dispatchers.Main) {
                        MyLogger.e("Server error: ${response.status.value}")
                    }
                    return@launch
                }

                val responseBody: String = response.body()
                MyLogger.d("getActualsAsync response=" + responseBody)

                // Инициализируем Moshi и адаптер
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter = moshi.adapter(ActualsResponse::class.java)

                val actualsResponse = try {
                    jsonAdapter.fromJson(responseBody)
                } catch (e: JsonDataException) {
                    MyLogger.e("JSON parsing error=" + e)
                    null
                }

                withContext(Dispatchers.Main) {
                    if (actualsResponse != null) {
                        MyLogger.d("Parsed Data: $actualsResponse")
                        MyLogger.d("actualResponse.stories: ${actualsResponse.stories.size}")
                        this@HomeViewModel.actualsResponse.value = actualsResponse
                    } else {
                        MyLogger.e("Failed to parse JSON")
                    }
                }
            } catch (e: ConnectTimeoutException) {
                MyLogger.e("No internet connection=" + e)
            } catch (e: Exception) {
                MyLogger.e("Error fetching data=" + e)
            }
        }
    }

    fun showActuals() {
        if (MyInfo.actualsResponse == null) {
            getActualsAsync()
        } else {
        }
    }


}