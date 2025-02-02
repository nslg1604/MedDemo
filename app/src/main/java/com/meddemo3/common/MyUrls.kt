package com.meddemo3.common

object MyUrls {
        val USER_ID = "?user_id="
        val URL_USER_INFO = "https://lk.k31.ru/api/v6/user/index/"  // recognized by token?
        val URL_SEND_PHONE = "https://lk.k31.ru/api/v6/element/user/request-otp"
        val URL_SEND_SMS = "https://lk.k31.ru/api/v6/element/user/auth-otp"
        val URL_SEND_PIN = "https://lk.k31.ru/api/v6/element/user/set-pin"
        val URL_SEND_REG = "https://lk.k31.ru/api/v6/element/user/simple-signup/"
        val URL_GET_TOKEN = "https://lk.k31.ru/api/v6/auth/login?device_uuid="
        val GET_ACTUAL = "https://lk.k31.ru/api/v6/main/actual/"
        val GET_BANNERS = "https://lk.k31.ru/api/v6/info/banner/"

        //https://lk.k31.ru/api/v6/info/banner/?user_id=
        val GET_POPULAR = "https://lk.k31.ru/api/v6/popular/"
        val GET_DOCTORS = "https://lk.k31.ru/api/v6/doctor"

        val USE_TERMS_PDF = "https://www.k31.ru/upload/doc/useterms.pdf"
        val USER_RULES_PDF = "https://www.k31.ru/uploads/licenses/2023-11-03/6544e9fb5d5df.pdf"
        val USER_AGREEMENT = "https://www.k31.ru/info/soglasie/"
        val URL_DEPARTMENTS = "https://lk.k31.ru/api/v6/department/"
        val URL_DOCTORS_BY_DEP = "https://lk.k31.ru/api/v6/doctor/by-department/"
        val URL_DOCTOR_DETAIL = "https://lk.k31.ru/api/v6/doctor/detail/"
        val URL_CLINICS = "https://lk.k31.ru/api/v6/clinic/"
        val URL_SLOTS =
            "https://lk.k31.ru/api/v5/element/schedule/schedule/doctor-days/?scope=lk&res_id="
        val URL_FAVORITE_ADD = "https://lk.k31.ru/api/v6/favorite/doctor-add/?user_id="
        val URL_FAVORITE_DELETE = "https://lk.k31.ru/api/v6/favorite/doctor-delete/?user_id="
        val URL_ESIA = "https://lk.k31.ru/esia/sign-phone"
        val URL_ESIA_CALLBACK = "com.k31.lk://oauth_callback?token="
        val URL_EXCHANGE_ESIA_TOKEN = "https://lk.k31.ru/api/v2/element/user/token-otp/?token="
        val URL_CARD = "https://lk.k31.ru/api/v2/element/patient-history/group/?user_id="
        var URL_CARD_DOCTORS = "https://lk.k31.ru/api/v1/service/doctor/?ex_id="

        //        var URL_CARD_DOCTORS = "https://lk.k31.ru/api/v1/service/doctor/?ex_id=485:935:757:870:870:757:757:757:757:753:795:647:485:643:722:496:735:667:1468:1656:1398:892:1398:1074:892:855:3282:855:3282:855"
        var URL_APPOINT = "https://lk.k31.ru/api/v2/element/patient-history/view/?user_id=%d&id=%d"
        val URL_CARD_DIAGNOSIS = "https://lk.k31.ru/api/v6/element/event/diagnosis?user_id="
        val URL_CARD_PLANS = "https://lk.k31.ru/api/v6/element/event/plan?user_id="
        val URL_CARD_ANALYSIS = "https://lk.k31.ru/api/v2/element/patient-history/files/?user_id="
        val URL_SEND_REVIEW = "https://lk.k31.ru/api/v1/form/nps-doctor/?user_id="
        val URL_CALL_DOCTOR = "https://lk.k31.ru/api/v1/form/home?user_id="
        val URL_SUGGESTION_ADDRESS = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address"
        //        val DOCTORS_ONLINE = "https://lk.k31.ru/api/v5/element/schedule/schedule/?scope=lk&is_telemed=1"
        val DOCTORS_ONLINE = "https://lk.k31.ru/api/v6/element/schedule/schedule/?scope=lk"

        //      example: URL_APPOINT = "https://lk.k31.ru/api/v2/element/patient-history/view/?user_id=8&id=3726540"
        // https://lk.k31.ru/api/v5/element/schedule/schedule/doctor-days/?scope=lk&res_id=435:985
        //https://docs.google.com/viewer?url=http://my.domain.com/yourPdfUrlHere.pdf
}
