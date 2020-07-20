package com.hmelikyan.architecture.shared.data

import com.hmelikyan.architecture.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IFirstService{

    @GET("todos/{id}")
    suspend fun getTodosForUser(@Path("id") id:Int):Response<List<ResponseItem>>

}