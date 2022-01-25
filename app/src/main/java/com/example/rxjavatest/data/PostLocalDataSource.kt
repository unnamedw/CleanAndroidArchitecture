package com.example.rxjavatest.data

import com.example.rxjavatest.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(): PostDataSource {
    override fun getPost(id: Int): Observable<Post?> {
        return Observable.just(null)
    }
}