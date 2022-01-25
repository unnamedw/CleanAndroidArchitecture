package com.example.cleanandroidarchitecture.data.network

import com.example.cleanandroidarchitecture.data.PostDataSource
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