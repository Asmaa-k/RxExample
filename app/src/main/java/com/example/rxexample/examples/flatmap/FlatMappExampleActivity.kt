package com.example.rxexample.examples.flatmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxexample.R
import com.example.rxexample.model.Post
import com.example.rxexample.requests.ServiceGenerator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_flatmap_example.*


class FlatMappExampleActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // vars
    private val disposables: CompositeDisposable = CompositeDisposable()

    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flatmap_example)

        initRecyclerView()
        getPostsObservable()
            .flatMap {
                return@flatMap getCommentsObservable(it);
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                updatePost(it)
            })

    }

    private fun updatePost(post: Post?) {
        adapter?.updatePost(post)
    }
    private fun getCommentsObservable(post: Post): Observable<Post?>? {
        return ServiceGenerator.requestApi.getComments(post.id)
            .map {
                post.comments = it
                return@map post
            }
            .subscribeOn(Schedulers.io())
    }
    private fun getPostsObservable(): Observable<Post> {
        return ServiceGenerator.requestApi
            .posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap{
                adapter?.setPosts(it)
                return@flatMap Observable.fromIterable(it)
                    .subscribeOn(Schedulers.io());
            }
    }


    private fun initRecyclerView() {
        adapter = RecyclerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}