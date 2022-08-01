package com.example.demo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btSubmit = findViewById<Button>(R.id.btSubmit)
        btSubmit.setOnClickListener {
            val username = sharedPreferences.getString("username", "")
            val password = sharedPreferences.getString("password", "")
            if(username!!.isNotEmpty()){
                if(username==etUsername.text.toString()){
                    if(password==etPassword.text.toString() && etPassword.text.toString().isNotEmpty()){
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Welcome::class.java))
                    }else{
                        Toast.makeText(this, "Wrong Password!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
            }else{
                if(etPassword.text.toString()==etConfirmPassword.text.toString()){
                    with(sharedPreferences.edit()){
                        putString("username", etUsername.text.toString())
                        putString("password", etPassword.text.toString())
                        apply()
                    }
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Welcome::class.java))
                }else{
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}