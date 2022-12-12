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
    const val USER_EMAIL_KEY = "user_email_key"
    const val MOBILE_NUMBER_KEY = "mobile_number_key"
    const val PASSWORD_KEY = "mobile_password_key"
    const val IS_USER_LOGIN = "is_user_login_key"
    const val LAT_KEY = "lat_key"
    const val LONG_KEY = "long_key"
    const val IS_LOCATION_FETCHED = "is+location_fetched_key"
    const val SELECTION_TYPE = "selection_type_key"
    const val SELECT_PICTURE = 1002
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val IMAGE = "item_image"
    const val PICK_UP_TIME = "pickup_time"
    const val QTY = "qty"
    const val COLLATION_ITEMS = "items"
}

