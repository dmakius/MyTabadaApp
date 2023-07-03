package com.cs683.q1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailEditText = findViewById<EditText>(R.id.emailEditText)
        var textView = findViewById<TextView>(R.id.greetingTextView)

        emailEditText.setOnEditorActionListener { exampleView, actionId, event ->
            if (actionId == EditorInfo.IME_NULL && event.action == KeyEvent.ACTION_DOWN
            ) {
                var name =  emailEditText.text.split("@")[0]
                textView.setText("Hi, " + name)

            }
            true
        }
    }
}