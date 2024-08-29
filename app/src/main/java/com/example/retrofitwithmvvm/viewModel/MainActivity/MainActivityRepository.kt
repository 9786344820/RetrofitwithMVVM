package com.example.retrofitwithmvvm.viewModel.MainActivity

import com.example.retrofitwithmvvm.api.ApiService
import com.example.retrofitwithmvvm.api.RetrofitInstance
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val apiService: ApiService) {
//    private var api = RetrofitInstance.api
    suspend fun getData() = apiService.getData()
}