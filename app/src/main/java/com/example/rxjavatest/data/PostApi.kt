package com.example.rxjavatest.data

import com.example.rxjavatest.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts/{post_id}")
    fun getPost(@Path("post_id") id: Int): Observable<Post?>

}