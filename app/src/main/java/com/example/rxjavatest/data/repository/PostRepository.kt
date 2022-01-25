package com.example.rxjavatest.data.repository

import com.example.rxjavatest.data.PostDataSource
import com.example.rxjavatest.data.PostLocalDataSource
import com.example.rxjavatest.data.PostRemoteDataSource
import com.example.rxjavatest.model.Post
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