package com.ngoprek.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<User> {
        val list = ArrayList<User>()
        if (cursor != null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritUserColumn.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritUserColumn.USERNAME))
                val avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritUserColumn.AVATAR_URL))
                list.add(
                    User(
                        username,
                        id,
                        avatar_url
                    )
                )
            }
        }
        return list
    }
}