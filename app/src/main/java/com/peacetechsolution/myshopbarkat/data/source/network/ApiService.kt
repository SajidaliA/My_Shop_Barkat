package com.peacetechsolution.myshopbarkat.data.source.network

import com.peacetechsolution.myshopbarkat.data.model.ProductResponse
import com.peacetechsolution.myshopbarkat.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constant.GET_USERS)
    suspend fun getUsers(@Query("page") page: Int): Response<ProductResponse>
}
