package com.example.rxexample.examples.debounce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxexample.R
import com.jakewharton.rxbinding2.widget.textChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_debounce_example.*
import java.util.concurrent.TimeUnit


class DebounceExampleActivity : AppCompatActivity() {


    private val TAG = "DebounceExampleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debounce_example)
        //first Example
        firstSimpleExample()
    }

    fun firstSimpleExample() {
        val subscribe = query.textChangeEvents()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val changedMessage: String = it.text().toString()
                Log.d(TAG, "firstSimpleExample: " + changedMessage)
            }
    }

}


