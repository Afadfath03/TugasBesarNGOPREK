package com.ngoprek.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoritviewModel(application: Application) : AndroidViewModel(application) {
    private var list = MutableLiveData<ArrayList<User>>()

    fun setFavoritUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoritUserColumn.CONTENT_URL,
            null,
            null,
            null,
            null
        )
        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listConverted)
    }

    fun getFavoriteUser(): LiveData<ArrayList<User>>? {
        return list
    }
}