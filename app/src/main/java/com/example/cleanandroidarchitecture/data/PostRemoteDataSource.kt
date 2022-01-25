package com.example.cleanandroidarchitecture.data

import com.example.cleanandroidarchitecture.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postApi: PostApi
): PostDataSource {

    override fun getPost(id: Int): Observable<Post?> {
        return postApi.getPost(id)
    }
}