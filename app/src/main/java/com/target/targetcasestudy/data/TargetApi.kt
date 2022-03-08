package com.target.targetcasestudy.data

import retrofit2.Call
import retrofit2.http.GET

interface TargetApi {
    @GET("deals")
    fun fetchDealData(): Call<Products>
}