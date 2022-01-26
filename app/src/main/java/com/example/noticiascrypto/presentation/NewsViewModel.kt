package com.example.noticiascrypto.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.noticiascrypto.repository.NewsRepository
import com.example.noticiascrypto.utils.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val repo: NewsRepository): ViewModel() {

    fun fetchLatestNews() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repo.getLatestNews("technology")))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }



}

class NewsViewModelFactory(private val repo: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance(repo)
    }
}