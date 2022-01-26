package com.example.noticiascrypto.repository

import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.model.DataList

interface NewsRepository {

    suspend fun getLatestNews(category: String): DataList

}