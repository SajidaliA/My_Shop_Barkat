package com.peacetechsolution.myshopbarkat.data.repository

import android.content.Context
import com.peacetechsolution.myshopbarkat.data.model.ProductResponse
import com.peacetechsolution.myshopbarkat.data.source.network.ApiService
import com.peacetechsolution.myshopbarkat.data.source.network.BaseApiResponse
import com.peacetechsolution.myshopbarkat.data.source.network.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext context: Context
) : BaseApiResponse(context) {

    suspend fun getUsers(page: Int): NetworkResult<ProductResponse> {
        return safeApiCall { apiService.getUsers(page) }
    }
}
