package com.example.linkedin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {
    private val userList = SplashScreen.allUsers
    private val str = MainActivity.str
    private val inputUser: User? = userList[str]
    private val s = MainActivity.s
    private val f = MainActivity.f
    private val w = MainActivity.w
    private val u = MainActivity.u

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suggestions)
        val text:TextView = findViewById(R.id.prt)
        if(s){
            text.text = getString(R.string.prt_spec)
        }
        if(f){
            text.text = getString(R.string.prt_field)
        }
        if(w){
            text.text = getString(R.string.prt_workplace)
        }
        if(u){
            text.text = getString(R.string.prt_uni)
        }
        val closeConnections: List<String> = traverse()

        val list: ArrayList<String> = analyze(closeConnections)
        if(list.size==0){
            list.add("No New Suggestions!")
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
            MainActivity.s = false
            MainActivity.f = false
            MainActivity.w = false
            MainActivity.u = false
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
            for(x1 in temp.toSet()){
                userList[x1]!!.degree = i
            }
            sList.union(temp)
        }
        return sList
    }

    private fun analyze(all: List<String>): ArrayList<String> {
        val suggestions: ArrayList<String> = ArrayList()
        var score = 0.0
        for (x in all) {
            if (inputUser != null) {
                userList[x]?.let { score = inputUser.giveScore(it, s, f, w, u) }
                if (score >= 450) {
                    userList[x]?.let { suggestions.add(it.id + "  |  " + it.name + "  |  " + it.field + "  |  " + it.workplace + "  |  " + it.universityLocation) }
                }
            }
        }
        return suggestions
    }
}
