package com.example.rxexample.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Post(
    @Expose @SerializedName("userId") var userId: Int,
    @Expose @SerializedName("id") var id: Int,
    @Expose @SerializedName("title") var title: String,
    @Expose @SerializedName("body") var body: String,
    var comments: List<Comment>?
)