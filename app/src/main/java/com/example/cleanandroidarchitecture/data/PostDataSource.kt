package com.example.cleanandroidarchitecture.data

import com.example.cleanandroidarchitecture.model.Post
import io.reactivex.Observable

interface PostDataSource {

    fun getPost(id: Int): Observable<Post?>

    fun getAllPosts(): Observable<List<Post>?>

}