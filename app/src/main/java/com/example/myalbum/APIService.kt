package com.example.myalbum

import com.example.myalbum.models.Album
import com.example.myalbum.models.Photo
import com.example.myalbum.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("users")
    suspend fun getUsers(): Response<MutableList<User>>

    @GET("albums")
    suspend fun getAlbums(): Response<MutableList<Album>>

    @GET("photos/{albumId}")
    suspend fun getPhotos(@Path("albumId") albumId : Int): Response<MutableList<Photo>>
}