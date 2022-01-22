package com.example.linkedin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private val userList = SplashScreen.allUsers
    private val str = MainActivity.str
    private val inputUser: User? = userList[str]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suggestions)

        var closeConnections: ArrayList<String> = gather()

        var list: ArrayList<String> = analyze()


        val btn = findViewById<Button>(R.id.back)
        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gather(): ArrayList<String> {
        var sList: ArrayList<String> = ArrayList()

        return sList
    }

    private fun analyze(): ArrayList<String> {
        var suggestions: ArrayList<String> = ArrayList()

        return suggestions
    }
}