package com.example.demo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "")
        val tvTitle = findViewById<TextView>(R.id.tvTitleWelcome)
        tvTitle.text = "Welcome $username!"

        val btDelete = findViewById<Button>(R.id.btDelete)
        btDelete.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show()
        }
    }
}