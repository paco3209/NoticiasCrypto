package com.example.noticiascrypto.repository

import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.model.DataList
import com.example.noticiascrypto.data.remote.NewsDataSource

class NewsRepositoryImpl(
    private val dataSource: NewsDataSource
): NewsRepository {
    override suspend fun getLatestNews(category: String): DataList {
        return dataSource.getLatestNews(category)
    }
}