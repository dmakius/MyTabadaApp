package com.example.cs683_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG= javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        Log.d(TAG, "OnStart Working")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "OnResume Working")
        super.onResume()
    }

    override fun onPause(){
        Log.d(TAG, "OnPause Working")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "OnStop Working")
        super.onStop()
    }

    override fun onRestart(){
        Log.d(TAG, "OnRestart Working")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "OnDestroy Working")
        super.onDestroy()
    }
}