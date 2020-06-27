package com.example.rxexample.examples.otherOperater

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxexample.R
import com.example.rxexample.model.Task
import io.reactivex.Observable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CreatExampleActivity : AppCompatActivity() {
    private val TAG = "CreatExampleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_exsmple)

        //creat
        val task = Task("Make dinner", false, 1)
        Observable.create<Task> {
            emitter ->
            emitter.onNext(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onNext: COMPLETE: " + it.description)
            }


        //range
        Observable.range(0, 9)
            . subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onNext: COMPLETE: " + it)
            }

        //range and optinal repet
        Observable.range(0, 9)
            .repeat(3)
            . subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onNext: COMPLETE: " + it)
            }

        //just =>take max of 10 oject
        val list = listOf<Int>(10,20,30,40,50,60,70,80,90,100)
        Observable.just(list,400,500,600)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onNext: COMPLETE: " + it)
            }


    }
}