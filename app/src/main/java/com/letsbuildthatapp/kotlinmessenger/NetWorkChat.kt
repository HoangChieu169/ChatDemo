package com.letsbuildthatapp.kotlinmessenger

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class NetWorkChat :Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}