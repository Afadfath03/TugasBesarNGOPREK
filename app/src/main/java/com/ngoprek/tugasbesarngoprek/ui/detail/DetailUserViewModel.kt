package com.ngoprek.tugasbesarngoprek.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngoprek.tugasbesarngoprek.api.RetrofitClient
import com.ngoprek.tugasbesarngoprek.data.local.FavoriteUserDao
import com.ngoprek.tugasbesarngoprek.data.local.UserDatabase
import com.ngoprek.tugasbesarngoprek.data.local.FavoriteUser
import com.ngoprek.tugasbesarngoprek.data.model.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()

    private var UserDao: FavoriteUserDao?
    private var UserDB: UserDatabase?

    init {
        UserDB = UserDatabase.getDatabase(application)
        UserDao = UserDB?.favoriteUserDao()
    }

    fun setUserDetail(username: String?) {
        RetrofitClient.apiInstance
            .getUserDetails(username)
            .enqueue(object : Callback<DetailUserResponse> {

                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", " tmssg : ${t.message.toString()}")
                }
            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarURL: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id,
                avatarURL
            )
            UserDao?.addToFavourite(user)
        }
    }

    suspend fun checkUser(id: Int) = UserDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            UserDao?.removeFromFavorite(id)
        }
    }
}