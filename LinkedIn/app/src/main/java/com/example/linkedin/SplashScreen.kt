package com.example.linkedin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.IOException

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    companion object {
        var allUsers: HashMap<String, User> = HashMap()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        getAssetJsonData(applicationContext)?.let { it1 -> readJSON(it1) }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
    private fun readJSON(str: String) {
        val mapper = jacksonObjectMapper()
        val userListFromJson: List<User> = mapper.readValue(str)
        for (x in userListFromJson) {
            allUsers[x.id] = x
            println(allUsers[x.id])
        }
    }

    private fun getAssetJsonData(context: Context): String? {
        val json: String? = try {
            val `is` = context.assets.open("users.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}
