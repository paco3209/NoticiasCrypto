package com.example.noticiascrypto.data.remote

import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.model.DataList
import com.example.noticiascrypto.repository.NewsApi

class NewsDataSource(private val webservice: NewsApi) {

    suspend fun getLatestNews(category: String): DataList = webservice.getLatestNews(category)
}