package com.example.firstassignment_mobileapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // load second screen
        setContentView(R.layout.activity_secondary)  // make sure the name matches!

        val tvMessage: TextView = findViewById(R.id.tvMessage)
        val btnBack: Button = findViewById(R.id.btnBack)

        // get message text
        val message = intent.getStringExtra(MainActivity.EXTRA_BUTTON_TEXT) ?: "No message"
        tvMessage.text = message

        btnBack.setOnClickListener { finish() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // back button information
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
