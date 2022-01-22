package com.example.linkedin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private val userList = SplashScreen.allUsers
    private val str = MainActivity.str
    private val inputUser: User? = userList[str]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suggestions)

        val closeConnections: List<String> = traverse()

        val list: ArrayList<String> = analyze(closeConnections)
        if(list.size==0){
            list.add("No Suggestions, Your Connection is up-to-date")
        }
        val arrayAdapter: ArrayAdapter<*>
        val mListView = findViewById<ListView>(R.id.userList)
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, list
        )
        mListView.adapter = arrayAdapter


        val btn = findViewById<Button>(R.id.back)
        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun traverse(): List<String> {
        val sList: ArrayList<String> = inputUser!!.connectionId as ArrayList<String>
        for (i in 1..5) {
            val temp: ArrayList<String> = ArrayList()
            val listAccountCloned = sList.toMutableList()
            for (x in listAccountCloned) {
                userList[x]?.let { temp.addAll(it.connectionId) }
            }
            sList.union(temp)
        }
        return sList
    }

    private fun analyze(all: List<String>): ArrayList<String> {
        val suggestions: ArrayList<String> = ArrayList()
        var score = 0
        for (x in all) {
            if (inputUser != null) {
                userList[x]?.let { score = inputUser.giveScore(it) }
                if (score >= 0) {
                    userList[x]?.let { suggestions.add(it.id + "       " + it.name + "       " + it.field) }
                }
            }
        }
        return suggestions
    }
}
