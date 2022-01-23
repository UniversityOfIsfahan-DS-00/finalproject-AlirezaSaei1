package com.example.linkedin


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.EditText





class MainActivity : AppCompatActivity() {
    private val x = SplashScreen.allUsers

    companion object {
        var str: String = ""
        var s: Boolean = false
        var w: Boolean = false
        var f: Boolean = false
        var u: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioG = findViewById<RadioGroup>(R.id.radio_Group)
        radioG.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.workplace -> {
                        w = true
                    }
                    R.id.uni -> {
                        u = true
                    }
                    R.id.field -> {
                        f = true
                    }
                    R.id.specs -> {
                        s = true
                    }
                    else -> {}
                }
            })
        val txt: TextInputEditText = findViewById(R.id.input)
        val btn1 = findViewById<Button>(R.id.suggestion)
        btn1.setOnClickListener {
            str = txt.text.toString()
            if (x[str] != null) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "ID is Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}

