package com.example.cleanandroidarchitecture.data.db

import com.example.cleanandroidarchitecture.data.PostDataSource
import com.example.cleanandroidarchitecture.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(): PostDataSource {
    override fun getPost(id: Int): Observable<Post?> {
        return Observable.just(null)
    }

    override fun getAllPosts(): Observable<List<Post>?> {
        return Observable.just(null)
    }
}