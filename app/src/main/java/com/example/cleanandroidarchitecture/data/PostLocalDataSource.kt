package com.example.cleanandroidarchitecture.data

import com.example.cleanandroidarchitecture.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(): PostDataSource {
    override fun getPost(id: Int): Observable<Post?> {
        return Observable.just(null)
    }
}