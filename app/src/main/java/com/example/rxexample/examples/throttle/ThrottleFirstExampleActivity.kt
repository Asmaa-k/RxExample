package com.example.rxexample.examples.throttle

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rxexample.R
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_buffer_example.*
import java.util.concurrent.TimeUnit

class ThrottleFirstExampleActivity : AppCompatActivity() {
    private val TAG = "ThrottleFirstExampleActivity"
    var timeSinceLastRequest = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer_example)
        firstSimpleExample()
    }


    fun firstSimpleExample() {
        val taskObserv = btn.clicks().map { return@map 1 }
            .throttleFirst(4000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "onNext: time since last clicked: " +
                        (System.currentTimeMillis() - timeSinceLastRequest) + " times in 4 sec")
                timeSinceLastRequest = System.currentTimeMillis()
                Toast.makeText(this, "you clicked the button!", Toast.LENGTH_SHORT).show()
            }
    }
}