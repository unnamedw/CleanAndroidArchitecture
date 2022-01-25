package com.example.cleanandroidarchitecture.data.repository

import com.example.cleanandroidarchitecture.data.PostDataSource
import com.example.cleanandroidarchitecture.data.PostLocalDataSource
import com.example.cleanandroidarchitecture.data.PostRemoteDataSource
import com.example.cleanandroidarchitecture.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostLocalDataSource
): PostDataSource {

    override fun getPost(id: Int): Observable<Post?> {
        return remoteDataSource.getPost(id)
    }


}