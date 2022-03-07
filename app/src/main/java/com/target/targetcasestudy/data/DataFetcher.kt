package com.target.targetcasestudy.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "DataFetcher"

class DataFetcher {
    private val targetApi: TargetApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
//      .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        targetApi = retrofit.create(TargetApi::class.java)
    }


    fun fetchDealData() : LiveData<String> {
        val responseLiveData : MutableLiveData<String> = MutableLiveData()
        val targetRequest : Call<String> = targetApi.fetchDealData()
        targetRequest.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch target data", t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }
        })
        return responseLiveData
    }
}