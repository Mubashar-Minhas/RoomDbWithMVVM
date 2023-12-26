package com.example.roomdbdemoapp

import android.app.Application

class MyApp : Application() {

    lateinit var repository: StudentRepository

    override fun onCreate() {
        super.onCreate()
        initializeRepository()
    }

    private fun initializeRepository() {
        // Initialize your Room database
        val database = StudentDatabase.getDatabase(this)
        // Initialize the DAO
        val studentDao = database.studentDao()
        // Initialize the repository
        repository = StudentRepository(studentDao)
    }


}
