package com.ngoprek.tugasbesarngoprek.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngoprek.tugasbesarngoprek.data.local.FavoriteUser
import com.ngoprek.tugasbesarngoprek.data.local.FavoriteUserDao
import com.ngoprek.tugasbesarngoprek.data.local.UserDatabase
import com.ngoprek.tugasbesarngoprek.data.model.DetailUserResponse

class FavoritviewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()

    private var UserDao: FavoriteUserDao?
    private var UserDB: UserDatabase?

    init {
        UserDB = UserDatabase.getDatabase(application)
        UserDao = UserDB?.favoriteUserDao()
    }

    fun getFavoriteUser():LiveData<List<FavoriteUser>>?{
        return UserDao?.getFavouriteUser()
    }
}