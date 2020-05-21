package com.example.testforandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserScreen : AppCompatActivity() {
    var image: ImageView? = null
    var email: TextView? = null
    var nameText: TextView? = null
    var lastNameText: TextView? = null
    var exitButton: Button? = null

    val APP_PREFERENCES = "current_user"
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userscreen)

        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        image = findViewById(R.id.image)
        email = findViewById(R.id.email)
        nameText = findViewById(R.id.nameText)
        lastNameText = findViewById(R.id.lastNameText)
        exitButton = findViewById(R.id.exitButton)

        image!!.setImageDrawable(getDrawable(pref.getInt("img",0)))
        nameText!!.text = pref.getString("name", null)
        lastNameText!!.text = pref.getString("lastName", null)
        email!!.text = pref.getString("email", null)


        exitButton!!.setOnClickListener { logOut() }
    }

    private fun logOut() {
        val editor = pref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        startActivity(Intent(this, MainActivity::class.java))
    }
}