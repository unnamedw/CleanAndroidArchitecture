package com.example.rxjavatest.data

import com.example.rxjavatest.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postApi: PostApi
): PostDataSource {

    override fun getPost(id: Int): Observable<Post?> {
        return postApi.getPost(id)
    }
}