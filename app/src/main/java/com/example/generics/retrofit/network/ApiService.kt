package com.example.generics.retrofit.network

import com.example.generics.retrofit.Posts
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("posts")
    suspend fun getPosts() : Response<List<Posts>>

    @GET("albums")
    suspend fun getAlbums() : Response<List<Posts>>


    companion object {
        const val BASE_URL = "https://jsonplaceholder.typecpde.com/"
    }
}