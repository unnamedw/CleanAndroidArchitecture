package com.example.rxjavatest.data

import com.example.rxjavatest.model.Post
import io.reactivex.Observable
import java.util.*

interface PostDataSource {

    fun getPost(id: Int): Observable<Post?>

}