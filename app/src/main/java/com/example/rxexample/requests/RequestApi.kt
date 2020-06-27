package com.example.rxexample.requests

import com.example.rxexample.model.Comment
import com.example.rxexample.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface RequestApi {
    @get:GET("posts")
    val posts: Observable<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(
        @Path("id") id: Int
    ): Observable<List<Comment>>
}