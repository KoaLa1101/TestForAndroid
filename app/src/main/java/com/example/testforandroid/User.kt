package com.example.testforandroid

class User(email: String, password: String, name: String, lastName: String, image: Int) {
    val email: String
    val password: String
    val name: String
    val lastName: String
    val image: Int

    init {
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.image = image

    }
}