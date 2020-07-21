package com.hmelikyan.architecture.shared.data

import com.hmelikyan.architecture.model.CommentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IFirstService{

    @GET("comments")
    suspend fun getTodosForUser(@Query("postId") id:Int):Response<List<CommentModel>>

}