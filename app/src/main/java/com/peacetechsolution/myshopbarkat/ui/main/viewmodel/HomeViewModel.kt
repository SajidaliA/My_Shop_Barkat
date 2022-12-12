package com.peacetechsolution.myshopbarkat.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peacetechsolution.myshopbarkat.data.model.ProductResponse
import com.peacetechsolution.myshopbarkat.data.repository.HomeRepository
import com.peacetechsolution.myshopbarkat.data.source.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {

    var userLiveData: MutableLiveData<NetworkResult<ProductResponse>> = MutableLiveData()
    private var page = 0

    fun updatePage() {
        page++
    }

    // with use of normal Coroutines methods.
    fun fetchUserData() {
        userLiveData.value = NetworkResult.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            userLiveData.postValue(homeRepository.getUsers(page))
        }
    }
}
