package com.example.rxexample.examples.buffer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxexample.R
import com.example.rxexample.util.DataSource
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_buffer_example.*
import java.util.concurrent.TimeUnit

class BufferExampleActivity : AppCompatActivity() {

    private val TAG = "BufferExampleActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer_example)
        //first Example
       // firstSimpleExample()
        secondSimpleExample()
    }

    fun firstSimpleExample() {
        val taskObserv = Observable.fromIterable(DataSource.createTasksList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(2)
            .subscribe {
             //note : the list "it" is array of size (2)
                Log.d(TAG, "onNext: Bundle Result:_____________ ")
                 for (task in it)
                    Log.d(TAG, "onNext: " + task.description)
            }
    }

    fun secondSimpleExample() {
        val taskObserv = btn.clicks().map { return@map 1 }
            .buffer(4, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "secondSimpleExample: you clicked " + it.size + " times in 4 sec")
            }
    }
}