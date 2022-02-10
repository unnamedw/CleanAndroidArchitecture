package com.example.cleanandroidarchitecture.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.data.repository.PostRepository
import com.example.cleanandroidarchitecture.model.PostItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PostRepository,
): BaseViewModel() {

    private val _posts: MutableLiveData<List<PostItemUiState>> = MutableLiveData()
    val posts: LiveData<List<PostItemUiState>> get() = _posts

    fun fetchData() {
        Log.d(AppConstants.TAG_APPLICATION, "fetchData called!")

        disposable.add(repository.getAllPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable{list -> list}
            .map { post ->
                PostItemUiState(
                    post = post,
                    onClick = {
                        Log.d(AppConstants.TAG_APPLICATION, "Item clicked! -> ${post.title}")
                    }
                )
            }
            .toList()
            .subscribe({ list ->
                Log.e(AppConstants.TAG_APPLICATION, "posts >> $posts")
                _posts.value = list
            }, { t ->
                Log.e(AppConstants.TAG_APPLICATION, "fetchData 에러! >> ", t)
                t.printStackTrace()
            }))
    }

}