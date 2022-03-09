package com.target.targetcasestudy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel : ViewModel() {
    val productsLiveData: LiveData<Products> = DataFetcher().fetchDealData()
}