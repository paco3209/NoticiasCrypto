package com.example.noticiascrypto.repository

import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.model.DataList
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("news")
    suspend fun getLatestNews(@Query("category") category: String = "technology") : DataList

}