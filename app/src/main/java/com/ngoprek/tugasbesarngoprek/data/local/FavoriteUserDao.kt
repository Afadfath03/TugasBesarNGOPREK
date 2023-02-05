package com.ngoprek.tugasbesarngoprek.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun addToFavourite(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM favorite_user")
    fun getFavouriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :Id")
    suspend fun checkUser(Id: Int): Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :Id")
    suspend fun removeFromFavorite(Id: Int): Int

    @Query("SELECT * FROM favorite_user")
    fun findAll(): Cursor
}