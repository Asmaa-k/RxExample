package com.example.rxexample.examples.fromIterable

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxexample.R
import com.example.rxexample.util.DataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FromIterableExampleActivity : AppCompatActivity() {
    private val TAG = "FromIterableExampleActivity"
    private var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fromitratable_example)
        compositeDisposable.add(
            Observable.fromIterable(DataSource.createTasksList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ task ->
                    Log.d(TAG, "onNext: COMPLETE: " + task.description)
                }, { error ->
                    Log.e(TAG, "onError: ", error)
                })
        )

        //If we want to follow the onNext(), onError() and onComplete() pattern of Subscriber, than we can use RxKotlin which is an extensions for RxJava

        /*
       if the code upove will block the main thread to prevent it from doing that you have to do this code on background thread
        add the following code (it will be executed on background thread)
        .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        return task.isComplete();
                    }
               })
         */
    }

    override fun onDestroy() {
        compositeDisposable.dispose()//hard clear
        super.onDestroy()
    }
}