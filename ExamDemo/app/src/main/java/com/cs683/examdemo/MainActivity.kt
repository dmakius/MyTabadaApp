package com.cs683.examdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    public Button b;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         b = findViewById(R.id.btnid);
        setContentView(R.layout.activity_main)
    }
}