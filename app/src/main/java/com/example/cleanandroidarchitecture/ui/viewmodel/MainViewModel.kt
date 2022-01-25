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
    private val repository: PostRepository
): BaseViewModel() {

    private val _post: MutableLiveData<Post> = MutableLiveData()
    val post: LiveData<Post> get() = _post

    fun fetchData() {
        Log.d(AppConstants.TAG, "fetchData called!")
        val samplePostId = 1

        disposable.add(repository.getPost(samplePostId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ post ->
                Log.d(AppConstants.TAG, "postData >> $post")
                _post.value = post
            }, { t ->
                Log.e(AppConstants.TAG, "fetchData 에러! >> ", t)
                t.printStackTrace()
            }))
    }

}