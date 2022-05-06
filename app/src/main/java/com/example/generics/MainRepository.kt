package com.example.generics

import com.example.generics.retrofit.network.ApiService
import com.example.generics.retrofit.utils.result
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getPosts() = result {
        apiService.getPosts()
    }

    fun getAlbums() = result {
        apiService.getPosts()
    }

}