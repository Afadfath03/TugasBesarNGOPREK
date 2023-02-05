package com.ngoprek.tugasbesarngoprek.api

import com.ngoprek.tugasbesarngoprek.data.model.DetailUserResponse
import com.ngoprek.tugasbesarngoprek.data.model.User
import com.ngoprek.tugasbesarngoprek.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    //Mencari User
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query:String
    ): Call<UserResponse>

    //Detail User
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetails(
        @Path("username") username: String?
    ): Call<DetailUserResponse>

    //Followers
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    //Following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}