package com.example.testforandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var login: TextView? = null
    var password: TextView? = null
    var welcomeText: TextView? = null
    var signInButton: Button? = null

    val APP_PREFERENCES = "current_user"
    lateinit var pref: SharedPreferences

    var myData = mutableListOf(
        User("a@mail.ru", "Qwert123", "Name1", "LastName1", R.drawable.a),
        User("b@mail.ru", "Qwert123", "Name2", "LastName2", R.drawable.b),
        User("c@mail.ru", "Qwert123", "Name3", "LastName3", R.drawable.c)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        if (pref.contains("img")) {
            startActivity(Intent(this, UserScreen::class.java))
        }

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        welcomeText = findViewById(R.id.welcomeText)
        signInButton = findViewById(R.id.signInButton)

        signInButton?.setOnClickListener { login() }

    }

    private fun login() {
        val email = login!!.text.toString()
        val pass = password!!.text.toString()
        var isExist = false

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            if (Regex("([0-9a-zA-Z]+[\\.\\-_]?)*[0-9a-zA-Z]+@([0-9a-zA-Z]+[\\.\\-_]?)+\\.[0-9a-zA-Z]+").find(
                    email) != null
                && Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$").find(pass) != null
            ) {
                for (user in myData) {
                    if (user.email == email && user.password == pass) {
                        val editor = pref.edit()
                        editor.putInt("img", user.image)
                        editor.putString("email", user.email)
                        editor.putString("name", user.name)
                        editor.putString("lastName", user.lastName)
                        editor.apply()
                        editor.commit()
                        isExist = true
                        Toast.makeText(this, "Successfull", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, UserScreen::class.java))
                        break

                    }
                }
                if(!isExist)
                    Toast.makeText(this, "Login or password error", Toast.LENGTH_LONG).show()
            }
        }
        else
            Toast.makeText(this, "Fill graphs email and password", Toast.LENGTH_LONG).show()
    }
}
