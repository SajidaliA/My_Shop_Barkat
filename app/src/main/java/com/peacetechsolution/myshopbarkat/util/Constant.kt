package com.peacetechsolution.myshopbarkat.util

/**
 * Created by Sajid.
 */

object Constant {
    const val AUTHORITY: String = "com.peacetechsolution.myshopbarkat.fileprovider"
    const val AUTHORISATION_TOKEN = "563492ad6f91700001000001f80e25ee327343d19bee70ca076493b5"
    const val BASE_URL = "https://api.pexels.com/v1/"
    const val GET_USERS = "search?query=mustang"
    const val UNAUTHORISED = 401
    const val REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
    const val PASSWORD_PATTERN_WITH_ONE_SPECIAL_CHARS = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"
    const val PERMISSION_REQUEST_CODE = 1001
    const val USER_NAME_KEY = "user_name_key"
    const val IS_USER_LOGIN = "is_user_login_key"
    const val LAT_KEY = "lat_key"
    const val LONG_KEY = "long_key"
    const val IS_LOCATION_FETCHED = "is+location_fetched_key"
    const val SELECTION_TYPE = "selection_type_key"
    const val URL_PATH = "url_path"
    const val PEACE_TECH_WEB_URL = "http://www.peacetechsolution.com"
    const val LESS_THEN_100 = "< 100"
    const val _100_TO_250 = "100-250"
    const val _250_TO_500 = "250-500"
    const val _500_TO_1000 = "500-1000"
    const val _1000_OR_MORE = ">1000"
    const val LAST_30_DAYS = "Last 30 Days"
    const val LAST_6_MONTHS = "Last 6 Months"
    const val LAST_1_YEAR = "Last 1 Year"
    const val SHOW_ALL = "Show All"
}

