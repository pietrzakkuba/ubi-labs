package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG = "StateChange"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }
//    override fun onConfigurationChanged() {
//        super.onConfigurationChanged()
//        Log.i(TAG, "onConfigurationChanged")
//    }
//    override fun onRestoreInstanceState() {
//        super.onRestoreInstanceState()
//        Log.i(TAG, "onRestoreInstanceState")
//    }
//    override fun onSaveInstanceState() {
//        super.onSaveInstanceState()
//        Log.i(TAG, "onSaveInstanceState")
//    }
}
