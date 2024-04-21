package com.peacetechsolution.myshopbarkat.ui.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.peacetechsolution.myshopbarkat.R
import com.peacetechsolution.myshopbarkat.util.isMobileNoValid
import com.peacetechsolution.myshopbarkat.util.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    var mobileNo: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    lateinit var errorMessages: MutableLiveData<String>

    fun isValidEmailAndPassword(context: Context): Boolean {
        return when {
            !mobileNo.value.isMobileNoValid() -> {
                errorMessages.value = context.getString(R.string.please_enter_valid_mobile_number)
                false
            }
            !password.value.isPasswordValid() -> {
                errorMessages.value = context.getString(R.string.invalid_password)
                false
            }
            else -> {
                true
            }
        }
    }
}
