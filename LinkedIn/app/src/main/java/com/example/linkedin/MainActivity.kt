package com.example.linkedin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private val x = SplashScreen.allUsers

    companion object {
        var str: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.suggestion)
        btn1.setOnClickListener {
            str = findViewById<TextInputEditText>(R.id.input).text.toString()
            if (x[str] != null) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "ID is Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

