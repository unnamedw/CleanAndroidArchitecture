package com.example.cleanandroidarchitecture.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.model.Post
import com.example.cleanandroidarchitecture.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PostRepository,
): BaseViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val post: LiveData<List<Post>> get() = _posts

    fun fetchData() {
        Log.d(AppConstants.TAG, "fetchData called!")
        val samplePostId = 1

        disposable.add(repository.getAllPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ posts ->
                Log.d(AppConstants.TAG, "postData >> $post")
                _posts.value = posts
            }, { t ->
                Log.e(AppConstants.TAG, "fetchData 에러! >> ", t)
                t.printStackTrace()
            }))
    }

}